package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AuthenticationServiceTest {
    private static final String ACCESS_TOKEN 
            = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmcmVzaCIsImlhdCI6MTY2NDM3Mjk4NSwiZXhwIjoxNjY0Mzc0Nzg1LCJlbWFpbCI6InRlc3RAZXhhbXBsZS5jb20ifQ.g5BxOtlZ4GQdWKO5T5CJwRW5CK6-2xMNLI4vMutKRBk";

    private static final Long ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";
    private static final String ENCODED_PASSWORD
            = "$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW";

    private AuthenticationService authService;

    private final UserRepository userRepo 
            = mock(UserRepository.class);

    private final RoleRepository roleRepo 
            = mock(RoleRepository.class);

    private final JwtUtil jwtUtil 
            = mock(JwtUtil.class);

    private PasswordEncoder passwordEncoder 
            = new BCryptPasswordEncoder();;

    @BeforeEach
    void setUp() {
        authService = new AuthenticationService(
                userRepo,
                roleRepo,
                jwtUtil,
                passwordEncoder
        );
    }

    @Test
    @DisplayName("로그인 성공")
    void login_success() {
        // given
        given(jwtUtil.makeAccessToken(EMAIL))
                .willReturn(ACCESS_TOKEN);

        User user = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .password(ENCODED_PASSWORD)
                .build();

        // when
        String accessToken = authService.login(user, PASSWORD);

        // then
        assertThat(accessToken).isEqualTo(ACCESS_TOKEN);
    }
}
