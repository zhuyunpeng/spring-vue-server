package com.zyp.springvueserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * @Date:17-12-12 下午5:28
 * @Author:root
 * @Desc:Jwt相关
 **/
public class JwtUtil {
    final static String base64EncodedSecretKey = "base64EncodedSecretKey";//私钥
    final static long TOKEN_EXP = 1000 * 60;//过期时间,测试使用60秒
    final static Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static String getToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    /**
     * @Date:17-12-12 下午6:21
     * @Author:root
     * @Desc:检查token,只要不正确就会抛出异常
     **/
    public static boolean checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            LOGGER.info("请求成功，token有效");
            return true;
        } catch (Exception e) {
            LOGGER.error("token check error:", e);
            return false;
        }
    }
}
