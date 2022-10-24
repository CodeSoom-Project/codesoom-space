package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

public class RetrospectiveUpdateServiceTest {

    private RetrospectiveUpdateService service;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @Mock
    private UserRepository userRepository;

    private User mockUser;
    private Reservation mockReservation;
    private Retrospective mockRetrospective;

    private final Long USER_ID = 1L;
    private final Long RETROSPECTIVE_ID = 1L;
    private final Long RESERVATION_ID = 1L;

    private final Long ANOTHER_RESERVATION_ID = 1000L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RetrospectiveUpdateService(reservationRepository);

        mockUser = User.builder()
                .id(USER_ID)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        mockRetrospective = Retrospective.builder()
                .content("잘했다.")
                .build();

        mockReservation = Reservation.builder()
                .id(RESERVATION_ID)
                .user(mockUser)
                .retrospective(mockRetrospective)
                .build();

        given(reservationRepository.findByIdAndUser_Id(RESERVATION_ID, USER_ID)).willReturn(Optional.of(mockReservation));

        given(retrospectiveRepository.findById(RETROSPECTIVE_ID)).willReturn(Optional.of(mockRetrospective));

    }

    @Test
    @DisplayName("예약한 회원이 회고 내용을 수정 하면 회고를 수정한다.")
    void will_update_content() {
        RetrospectiveRequest request = new RetrospectiveRequest("수정했다.");

        service.update(RESERVATION_ID, mockUser, request.getContent());

        assertThat(mockRetrospective.getContent()).isEqualTo(request.getContent());
    }

    @Test
    @DisplayName("예약을 소유하지 않은 회원이 회고 내용을 수정을 하면 NotOwnedReservationException 예외를 던진다.")
    void will_throw_RetrospectiveNotFoundException() {
        assertThatThrownBy(() -> service.update(ANOTHER_RESERVATION_ID, mockUser, "수정했다."))
                .isInstanceOf(NotOwnedReservationException.class);
    }

}
