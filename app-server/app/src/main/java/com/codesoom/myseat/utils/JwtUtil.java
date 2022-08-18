package com.codesoom.myseat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 생성된 토큰을 반환한다.
     * 
     * @param userDetails
     * @return 토큰
     */
    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.put("username", userDetails.getUsername());
        
        return token(claims, userDetails.getUsername()); // username을 subject로 해서 token 생성
    }

    /**
     * 토큰을 반환한다.
     * 
     * @param claims
     * @param subject 
     * @return
     */
    private String token(Claims claims, String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 1시간
                .signWith(key)
                .compact();
    }

    /**
     * 토큰의 유효 여부를 확인한다.
     * 
     * @param token 토큰
     * @param userDetails User 정보
     * @return 토큰이 유효하면 true, 유효하지 않으면 false를 반환한다.
     */
    public Boolean isValidToken(String token, UserDetails userDetails) {
        log.info("isValidToken token = {}", token);
        String username = getUsernameFromToken(token);
        
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    /**
     * 토큰의 claims를 디코딩한다.
     * 
     * @param token 토큰
     * @return Claims
     */
    private Claims getAllClaims(String token) {
        log.info("getAllClaims token = {}", token);
        
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰의 username을 반환한다.
     * 
     * @param token 토큰
     * @return username
     */
    public String getUsernameFromToken(String token) {
        String username = String.valueOf(getAllClaims(token).get("username"));
        log.info("getUsernameFormToken subject = {}", username);
        
        return username;
    }

    /**
     * 토큰의 만료 날짜를 반환한다.
     * 
     * @param token 토큰
     * @return 만료 날짜
     */
    public Date getExpirationDate(String token) {
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }

    /**
     * 토큰의 만료 여부를 확인한다.
     * 
     * @param token 토큰
     * @return 토큰이 만료되었으면 true, 만료되지 않았으면 false를 반환한다.
     */
    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }
}
