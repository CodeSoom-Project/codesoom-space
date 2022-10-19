package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 회원 엔티티
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    /**
     * 비밀번호 인증에 성공하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
     * 
     * @param password 인증할 비밀번호
     * @param passwordEncoder 인코더
     * @return 비밀번호 인증에 성공하면 true, 그렇지 않으면 false
     */
    public boolean authenticate(
            String password, 
            PasswordEncoder passwordEncoder
    ) {
        log.info("password: " + password);
        log.info("this.password: " + this.password);
        return passwordEncoder.matches(password, this.password);
    }
}
