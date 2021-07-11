package com.ponder.jwtdemo.interceptor;

import com.ponder.jwtdemo.annotation.PassToken;
import com.ponder.jwtdemo.exception.NullTokenException;
import com.ponder.jwtdemo.service.UserService;
import com.ponder.jwtdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.logging.Handler;

/**
 * JwtAuthenticationInterceptor.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/7 15:15
 */
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // 如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        // 检查是否有 @PassToken 注解，有则跳过token验证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            return passToken.required();
        } else {
            System.out.println("被jwt拦截，需要进行token认证");
            if (token == null) {
                throw new NullTokenException();
            }
            // 获取token中的用户身份信息
            String username = JwtUtil.getAudience(token);
            if (!userService.checkUser(username)) {
                throw new NullTokenException("token身份信息无效，请登录");
            }
            // 验证token
            JwtUtil.verifyToken(token, username);
            // 获取载荷内容
            String password = JwtUtil.getClaimByName(token, "password").asString();
            String phoneNumber = JwtUtil.getClaimByName(token, "phoneNumber").asString();

            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("phoneNumber", phoneNumber);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
