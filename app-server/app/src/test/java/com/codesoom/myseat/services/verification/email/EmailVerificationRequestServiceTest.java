package com.codesoom.myseat.services.verification.email;

import com.codesoom.myseat.domain.EmailToken;
import com.codesoom.myseat.repositories.EmailTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmailVerificationRequestServiceTest {

    @InjectMocks
    private EmailVerificationRequestService service;

    @Mock
    private EmailTokenRepository repository;

    @DisplayName("주어진 유저 id로 생성된 토큰은 유효하다.")
    @Test
    void requestEmailVerificationTest() {
        //given
        Long userId = 1L;
        given(repository.save(any()))
                .willReturn(EmailToken.createEmailToken(userId));

        //when
        EmailToken token = service.requestEmailVerification(userId);

        //then
        assertThat(token.isExpired()).isFalse();
        assertThat(token.getExpirationDate()).isAfter(LocalDateTime.now());
    }

}
