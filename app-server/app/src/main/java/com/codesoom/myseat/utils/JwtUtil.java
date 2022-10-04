package com.codesoom.myseat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private final Key key;

    public JwtUtil(
            @Value("${jwt.secret}") String secret
    ) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 생성된 토큰을 반환한다.
     * 
     * @param email 토큰을 부여할 회원 이메일
     * @return 토큰 
     */
    public String makeAccessToken(String email) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("email", email)
                .signWith(key)
                .compact();
    }

    /**
     * 토큰을 파싱한 후 이메일을 반환한다.
     * 
     * @param accessToken
     * @return 이메일
     */
    public String parseAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        String email = claims.get("email", String.class);
        log.info("email: " + email);
        if(email == null) {
            log.info("@@@@@@@@@@@없어");
        }

        return email;
    }
}
