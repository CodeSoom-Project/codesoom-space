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
     * @param email
     * @return 토큰
     */
    public String makeJwtToken(String email) {
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

    public String parseJwtToken(String token) {
        log.info("token: " + token);

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        
        String email = claims.get("email", String.class);
        log.info("email: " + email);

        return email;
    }
}
