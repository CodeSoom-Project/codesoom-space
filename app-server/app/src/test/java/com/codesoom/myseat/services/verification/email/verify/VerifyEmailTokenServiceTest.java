package com.codesoom.myseat.services.verification.email.verify;

import com.codesoom.myseat.domain.EmailToken;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.VerifyEmailTokenRequest;
import com.codesoom.myseat.exceptions.EmailTokenException;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.repositories.EmailTokenRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VerifyEmailTokenServiceTest {

    @InjectMocks
    private VerifyEmailTokenService service;

    @Mock
    private EmailTokenRepository repository;

    @Mock
    private UserRepository userRepository;

    @DisplayName("유효한 토큰이 주어지면 유저의 Role에 VERIFIED_USER가 추가된다.")
    @Test
    void verifyEmailToken() {
        //given
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        given(repository.findById(anyString()))
                .willReturn(Optional.of(
                        EmailToken.createEmailToken(1L)));
        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(mockUser));

        //when
        service.verifyEmailToken(new VerifyEmailTokenRequest("dslkfajsidfwl"));

        //then
        assertThat(mockUser.getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList()))
                .contains("VERIFIED_USER");
    }

    @DisplayName("토큰을 찾을 수 없으면 EmailTokenException을 던진다.")
    @Test
    void throw_when_not_found_token() {
        given(repository.findById(anyString()))
                .willReturn(Optional.empty());

        assertThrows(EmailTokenException.class,
                () -> service.verifyEmailToken(
                        new VerifyEmailTokenRequest("dslkfajsidfwl")));
    }

    @DisplayName("토큰이 만료되었으면 EmailTokenException을 던진다.")
    @Test
    void throw_when_token_expired() {
        given(repository.findById(anyString()))
                .willReturn(
                        Optional.of(new EmailToken(
                                "dslkfajsidfwl",
                                LocalDateTime.now().minusMinutes(30L),
                                true,
                                1L))
                );

        assertThrows(EmailTokenException.class,
                () -> service.verifyEmailToken(
                        new VerifyEmailTokenRequest("dslkfajsidfwl")));
    }

    @DisplayName("토큰과 일치하는 userId로 유저를 찾을 수 없으면"
            + "UserNotFoundException을 던진다.")
    @Test
    void throw_when_user_not_found() {
        given(repository.findById(anyString()))
                .willReturn(Optional.of(
                        EmailToken.createEmailToken(1L)));
        given(userRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> service.verifyEmailToken(
                        new VerifyEmailTokenRequest("dslkfajsidfwl")));
    }

}
