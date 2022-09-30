package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.dto.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SignupServiceTest {
    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";

    private SignupService service;

    private final UserRepository userRepo 
            = mock(UserRepository.class);

    private final RoleRepository roleRepo
            = mock(RoleRepository.class);

    private PasswordEncoder passwordEncoder 
            = new BCryptPasswordEncoder();
    
    private SignupRequest request;
    private Role role;

    @BeforeEach
    void setUp() {
        service = new SignupService(
                userRepo,
                roleRepo,
                passwordEncoder
        );
    }

    @Nested
    @DisplayName("signUp 메서드는")
    class Describe_signUp_method {
        @BeforeEach
        void setUp() {
            request = SignupRequest.builder()
                    .name(NAME)
                    .email(EMAIL)
                    .password(PASSWORD)
                    .build();

            role = new Role(request.getEmail(), "USER");

            given(userRepo.save(any(User.class)))
                    .will(invocation -> {
                        return User.builder()
                                .id(USER_ID)
                                .name(NAME)
                                .email(EMAIL)
                                .password(PASSWORD)
                                .build();
                    });
            
            given(roleRepo.save(any(Role.class)))
                    .will(invocation -> {
                        return role;
                    });
        }

        @Nested
        @DisplayName("회원 정보를 반환한다")
        class It_returns_user_data {
            User subject() {
                return service.signUp(request);
            }

            @Test
            void test() {
                assertThat(subject().getId()).isEqualTo(USER_ID);
                assertThat(subject().getName()).isEqualTo(NAME);
                assertThat(subject().getEmail()).isEqualTo(EMAIL);
            }
        }
    }
}
