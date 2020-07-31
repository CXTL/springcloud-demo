package com.dupake.system.security;

/**
 * @ClassName JwtConfig
 * @Description TODO
 * @Author dupake
 * @Date 2020/5/25 10:12
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtConfig {
    public static final String REDIS_TOKEN_KEY_PREFIX = "TOKEN_";
    private static long time = 86400000;     // 过期时间
    private static String secret; // JWT密码
    private static String prefix; // Token前缀
    private static String header; // 存放Token的Header Key
    private static String claims; // 存放Token的Header Key

    public static long getTime() {
        return 86400000;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public static String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public static String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public static String getClaims() {
        return claims;
    }

    public  void setClaims(String claims) {
        this.claims = claims;
    }
}