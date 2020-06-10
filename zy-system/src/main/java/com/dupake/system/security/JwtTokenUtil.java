package com.dupake.system.security;

/**
 * @ClassName JwtTokenUtil
 * @Description TODO
 * @Author dupake
 * @Date 2020/5/25 16:53
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    /**
     * 生成Token
     */
    public static String createToken(String username, String role) {
        Map<String, Object> map = new HashMap<>();
        map.put(JwtConfig.getClaims(), role);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.getTime()))
                .signWith(SignatureAlgorithm.HS256, JwtConfig.getSecret()).compact();
        return token;
    }

    /**
     * 校验Token
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(JwtConfig.getSecret()).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtConfig.getSecret()).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 从Token中获取用户角色
     */
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtConfig.getSecret()).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtConfig.getSecret()).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
}