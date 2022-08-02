package com.codesoom.myseat.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * 토큰 생성
 */
@Component
public class TokenProvider {
    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 토큰을 반환한다.
     * 
     * @param email 회원 이메일
     * @return 토큰
     */
    public String token(String email) {
        return Jwts.builder()
                .claim("auth", email)
                .signWith(key)
                .compact();
    }
}
