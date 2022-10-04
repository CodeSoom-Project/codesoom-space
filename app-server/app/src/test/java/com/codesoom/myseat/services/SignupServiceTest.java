package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SignupServiceTest {
    private static final Long ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";
    private static final String ENCODED_PASSWORD 
            = "$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW";

    private SignupService service;

    private final UserRepository userRepo 
            = mock(UserRepository.class);

    private final RoleRepository roleRepo
            = mock(RoleRepository.class);

    private PasswordEncoder passwordEncoder 
            = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
        service = new SignupService(
                userRepo,
                roleRepo,
                passwordEncoder
        );
    }

    @Test
    @DisplayName("회원 생성 성공")
    void test() {
        // given
        given(roleRepo.save(any(Role.class)))
                .will(invocation -> Role.builder()
                        .email(EMAIL)
                        .roleName("USER")
                        .build());

        given(userRepo.save(any(User.class)))
                .will(invocation -> User.builder()
                        .id(ID)
                        .name(NAME)
                        .email(EMAIL)
                        .password(ENCODED_PASSWORD)
                        .build());

        // when
        User user = service.createUser(NAME, EMAIL, PASSWORD);

        // then
        assertThat(user.getId()).isEqualTo(ID);
        assertThat(user.getName()).isEqualTo(NAME);
        assertThat(user.getEmail()).isEqualTo(EMAIL);
        assertThat(user.getPassword()).isEqualTo(ENCODED_PASSWORD);
    }
}
