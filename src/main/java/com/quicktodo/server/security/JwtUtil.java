package com.quicktodo.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // 生成签名密钥
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 生成 JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // 设置主题
                .issuedAt(new Date()) // 设置签发时间
                .expiration(new Date(System.currentTimeMillis() + expiration)) // 设置过期时间
                .signWith(getSigningKey()) // 使用密钥签名
                .compact(); // 生成紧凑的 JWT 字符串
    }

    // 从 JWT 中提取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey()) // 设置签名密钥
                .build()
                .parseSignedClaims(token) // 解析 JWT
                .getPayload(); // 获取 Payload
        return claims.getSubject(); // 返回主题（用户名）
    }

    // 验证 JWT 是否有效
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey()) // 设置签名密钥
                    .build()
                    .parseSignedClaims(token); // 解析 JWT
            return true; // 如果解析成功，返回 true
        } catch (Exception e) {
            return false; // 如果解析失败，返回 false
        }
    }

    public void printTokenDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            System.out.println("JWT Payload: " + claims);
        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
    }

}
