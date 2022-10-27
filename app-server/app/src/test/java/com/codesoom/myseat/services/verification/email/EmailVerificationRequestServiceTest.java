package com.codesoom.myseat.services.verification.email;

import com.codesoom.myseat.domain.EmailToken;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.helpers.EmailHelper;
import com.codesoom.myseat.repositories.EmailTokenRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmailVerificationRequestServiceTest {

    @InjectMocks
    private EmailVerificationRequestService service;

    @Mock
    private EmailTokenRepository emailTokenRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailHelper helper;

    @DisplayName("주어진 유저 id로 생성된 토큰은 유효하다.")
    @Test
    void requestEmailVerificationTest() throws IOException {
        //given
        Long userId = 1L;

        User mockUser = User.builder()
                .id(userId)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        given(emailTokenRepository.save(any()))
                .willReturn(EmailToken.createEmailToken(userId));
        given(userRepository.findById(any()))
                .willReturn(Optional.of(mockUser));

        //when
        EmailToken token = service.requestEmailVerification(userId);

        //then
        assertThat(token.isExpired()).isFalse();
        assertThat(token.getExpirationDate()).isAfter(LocalDateTime.now());
    }

}
