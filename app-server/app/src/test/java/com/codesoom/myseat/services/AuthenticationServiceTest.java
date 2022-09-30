package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AuthenticationServiceTest {
    private static final String SECRET 
            = "12345678901234567890123456789012";

    private static final String VALID_TOKEN 
            = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmcmVzaCIsImlhdCI6MTY2NDM3Mjk4NSwiZXhwIjoxNjY0Mzc0Nzg1LCJlbWFpbCI6InRlc3RAZXhhbXBsZS5jb20ifQ.g5BxOtlZ4GQdWKO5T5CJwRW5CK6-2xMNLI4vMutKRBk";

    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";

    private AuthenticationService authService;

    private final UserRepository userRepo 
            = mock(UserRepository.class);

    private final RoleRepository roleRepo 
            = mock(RoleRepository.class);

    private final JwtUtil jwtUtil 
            = mock(JwtUtil.class);

    private PasswordEncoder passwordEncoder 
            = new BCryptPasswordEncoder();;

    private User user;

    @BeforeEach
    void setUp() {
        authService = new AuthenticationService(
                userRepo,
                roleRepo,
                jwtUtil,
                passwordEncoder
        );
    }

    @Nested
    @DisplayName("login 메서드는")
    class Describe_login_method {
        @BeforeEach
        void setUp() {
            user = User.builder()
                    .id(USER_ID)
                    .name(NAME)
                    .email(EMAIL)
                    .password(passwordEncoder.encode(PASSWORD))
                    .build();

            given(userRepo.findByEmail(eq(EMAIL)))
                    .willReturn(Optional.of(user));

            given(jwtUtil.makeAccessToken(any(String.class)))
                    .willReturn(VALID_TOKEN);
        }

        @Nested
        @DisplayName("토큰을 반환한다")
        class It_returns_token {
            String subject() {
                return authService.login(EMAIL, PASSWORD);
            }

            @Test
            void test() {
                assertThat(subject()).isEqualTo(VALID_TOKEN);
            }
        }
    }
}
