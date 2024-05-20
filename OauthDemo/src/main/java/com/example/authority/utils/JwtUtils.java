package com.example.authority.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class JwtUtils {
    
    @Value("${jwt.expiration}")
    private long expire;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.header}")
    private String header;
    
    /*
        生成JWT
     */
    public String generateToken(String username) {
        
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);
        
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)    // 7天过期
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    /*
        从 JWT 中提取用户名
     */
    public Claims getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    
    /*
        验证 JWT 的有效性。
        @param claims 从JWT中解析出的Claims对象
        @return 如果JWT有效则返回true，否则返回false
    */
    public boolean validateToken(Claims claims) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(claims.getSubject());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
}

