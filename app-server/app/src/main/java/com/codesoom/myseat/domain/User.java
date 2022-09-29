package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;

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
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    @Builder.Default
    private boolean haveSeat = false;

    @OneToMany(mappedBy = "user")
    private List<SeatReservation> seatReservations = new ArrayList<>();

    public boolean authenticate(
            String password, 
            PasswordEncoder passwordEncoder
    ) {
        log.info("password: " + password);
        log.info("this.password: " + this.password);
        return passwordEncoder.matches(password, this.password);
    }
    
    public void reserve() {
        this.haveSeat = true;
    }
    
    public void cancelReserve() {
        this.haveSeat = false;
    }
}
