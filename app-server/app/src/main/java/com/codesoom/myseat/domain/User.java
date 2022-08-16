package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String name;
    
    private String email;
    
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "token_id")
    private Token token;

    @OneToMany(mappedBy = "user")
    private List<SeatReservation> seatReservations = new ArrayList<>();

    /**
     * 비밀번호를 인증한다.
     * 
     * @param password 인증할 비밀번호
     * @param encoder 비밀번호 인코더
     * @return 엔티티의 비밀번호와 일치하면 true, 일치하지 않으면 false
     */
    public boolean authenticate(
            String password,
            PasswordEncoder encoder
    ) {
        return encoder.matches(password, this.password);
    }

    /**
     * 토큰을 갱신 한다.
     * 
     * @param token 토큰
     */
    public void updateToken(Token token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        
        for(String r : role.toString().split("_")) {
            roles.add(new SimpleGrantedAuthority(r));
        }
        
        return roles;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
