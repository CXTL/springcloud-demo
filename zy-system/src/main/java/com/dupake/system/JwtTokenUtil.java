package com.dupake.system;

/**
 * @ClassName JwtTokenUtil
 * @Description jwt 工具类
 * @Author dupake
 * @Date 2020/5/25 9:55
 */

import com.dupake.system.security.JwtConfig;
import com.dupake.system.security.MyUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.util.*;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 7965205899118624911L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLES = "roles";

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Value("jwt.secret")
    private static String SECRET;

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long)claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 从token中获取过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成过期时间 单位[ms]
     *
     */
    private Date generateExpirationDate() {
        // 当前毫秒级时间 + yml中的time * 1000
        return new Date(System.currentTimeMillis() + jwtConfig.getTime() * 1000);
    }

    /**
     * 根据提供的用户详细信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername()); // 放入用户名
        claims.put(CLAIM_KEY_CREATED, new Date()); // 放入token生成时间
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) { // SimpleGrantedAuthority是GrantedAuthority实现类
            // GrantedAuthority包含类型为String的获取权限的getAuthority()方法
            // 提取角色并放入List中
            roles.add(authority.getAuthority());
        }
        claims.put(CLAIM_KEY_ROLES, roles); // 放入用户权限

        return generateToken(claims);
    }

    /**
     * 创建token
     * @param username
     * @return
     */
    public  String createJwtToken(String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return generateToken(userDetails);
    }

    /**
     * 生成token（JWT令牌）
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }


    /**
     * 验证jwt的有效期
     * @param claims
     * @return
     */
    public static Boolean isTokenExpired(Claims claims) {
        final Date expiration =  claims.getExpiration();
        return expiration.before(new Date());
    }

    // Sample method to validate and read the JWT
    public static   Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        }catch (Exception exception){
            return null;
        }
    }


}
