package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.LoginFailureException;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserRepository userRepo, 
            RoleRepository roleRepo, 
            JwtUtil jwtUtil, 
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(
            String email, 
            String password
    ) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new LoginFailureException(email));

        if (!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailureException(email);
        }

        return jwtUtil.makeJwtToken(user.getEmail());
    }

    public String parseToken(
            String token
    ) {
        return jwtUtil.parseJwtToken(token);
    }

    public List<Role> roles(
            String email
    ) {
        return roleRepo.findAllByEmail(email);
    }
}
