package com.ponder.jwtdemo.config;

import com.ponder.jwtdemo.interceptor.JwtAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * JwtInterceptorConfig.java
 * JWT拦截器配置类
 * @author Ponder Yao
 * @version 1.0.0  2021/7/7 15:10
 */
@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public JwtAuthenticationInterceptor authenticationInterceptor() {
        return new JwtAuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
    }

}
