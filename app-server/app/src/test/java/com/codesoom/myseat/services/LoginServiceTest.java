package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Token;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.TokenRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.utils.TokenProvider;
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

class LoginServiceTest {
    private static final String SECRET = "12345678901234567890123456789012";

    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "코드숨";
    private static final String USER_EMAIL = "test@example.com";
    private static final String USER_PASSWORD = "1234";
    private static final Long TOKEN_ID = 2L;
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9" +
            ".eyJ1c2VySWQiOjF9" +
            ".ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private LoginService service;
    private final UserRepository userRepo = mock(UserRepository.class);
    private final TokenRepository tokenRepo = mock(TokenRepository.class);
    private PasswordEncoder encoder;
            
    private LoginRequest request;
    private User user;

    @BeforeEach
    void setUp() {
        TokenProvider tokenProvider = new TokenProvider(SECRET);
        encoder = new BCryptPasswordEncoder();
        service = new LoginService(userRepo, tokenProvider, tokenRepo, encoder);
    }

    @Nested
    @DisplayName("login 메서드는")
    class Describe_login_method {
        @BeforeEach
        void setUp() {
            request = LoginRequest.builder()
                    .email(USER_EMAIL)
                    .password(USER_PASSWORD)
                    .build();
            
            user = User.builder()
                    .id(USER_ID)
                    .name(USER_NAME)
                    .email(USER_EMAIL)
                    .password(encoder.encode(USER_PASSWORD))
                    .build();

            given(userRepo.findByEmail(eq(USER_EMAIL)))
                    .willReturn(Optional.of(user));

            given(tokenRepo.save(any(Token.class)))
                    .will(invocation -> Token.builder()
                            .id(TOKEN_ID)
                            .user(user)
                            .token(TOKEN)
                            .build());
        }

        @Nested
        @DisplayName("토큰을 반환한다")
        class It_returns_token {
            Token subject() {
                return service.login(request);
            }

            @Test
            void test() {
                assertThat(subject().getToken()).isEqualTo(TOKEN);
            }
        }
    }
}
