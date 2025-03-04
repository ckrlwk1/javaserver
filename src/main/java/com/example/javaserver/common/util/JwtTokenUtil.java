package com.example.javaserver.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;  // 비밀 키

    @Value("${jwt.expiration}")
    private long expirationTime;  // 만료 시간 (밀리초 단위)
//    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final SecretKey key = Keys.hmacShaKeyFor("J#9r$X1k8Lp2!I7BzvF8kS@lR^mZ@88pP2tQq5A7".getBytes());




//    private final Key key;
//
//    // 생성자에서 키를 초기화
//    public JwtTokenUtil() {
//        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 키 생성
//    }

    public String generateToken(String userId, String role) {
        System.out.println("expirationTime : " + expirationTime);
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role); // 역할 추

//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // HS256에 적합한 256비트 키 생성
//
        String jwt = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .setSubject(userId)
                .setClaims(claims)
                .signWith(key)
                .compact();

        return jwt;
    }

    public Claims extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // 서명에 사용된 키를 설정
                .build()
                .parseClaimsJws(token) // 토큰 파싱
                .getBody();

        // 토큰에서 Subject(사용자 ID) 추출
        return claims;
    }

    /**
     * 🔹 JWT 토큰 만료 여부 확인
     */
    public boolean isTokenExpired(String token) {
        return extractUsername(token).getExpiration().before(new Date());
    }

    // 토큰에서 사용자 이름 추출
//    public String extractUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(SECRET_KEY)  // SECRET_KEY 사용
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }



    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)  // SECRET_KEY 사용
                    .build()
                    .parseClaimsJws(token);  // 토큰 파싱 시 예외가 발생하면 유효하지 않음
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 예외가 발생하면 토큰이 유효하지 않음
            return false;
        }
    }
}
