package com.ponder.jwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ponder.jwtdemo.exception.TokenUnavailableException;

import java.util.Calendar;
import java.util.Date;

/**
 * JwtUtil.java
 * JSON Web Token 工具类
 * @author Ponder Yao
 * @version 1.0.0  2021/7/7 14:12
 */
public class JwtUtil {

    /**
     * 创建Token令牌
     * @param username 姓名
     * @param password 密码
     * @param phoneNumber 手机号
     * @return 字符串：Token令牌
     */
    public static String createToken(String username, String password, String phoneNumber) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);  //设置有效时间30分钟
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(username)   //签发对象
                .withIssuedAt(new Date())          //发行时间
                .withExpiresAt(expiresDate)        //有效时间
                .withClaim("password", password)    //载荷
                .withClaim("phoneNumber", phoneNumber)
                .sign(Algorithm.HMAC256(username + "HelloJwt"));  //加密
    }

    /**
     * 校验Token令牌合法性
     * @param token token令牌
     * @param secret 传入用户签发对象
     * @throws TokenUnavailableException 自定义token校验失败异常
     */
    public static void verifyToken(String token, String secret) throws TokenUnavailableException {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret + "HelloJwt")).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new TokenUnavailableException("Token令牌校验失败");
        }
    }

    /**
     * 从token令牌中获取签发对象
     * @param token token令牌
     * @return 字符串：签发对象audience
     * @throws TokenUnavailableException 自定义token解析失败异常
     */
    public static String getAudience(String token) throws TokenUnavailableException {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new TokenUnavailableException("Token令牌解析失败");
        }
        return audience;
    }

    /**
     * 通过token令牌的载荷名称获取载荷值
     * @param token token令牌
     * @param name 载荷名称
     * @return 字符串：载荷值
     */
    public static Claim getClaimByName(String token, String name) {
        return JWT.decode(token).getClaim(name);
    }

}
