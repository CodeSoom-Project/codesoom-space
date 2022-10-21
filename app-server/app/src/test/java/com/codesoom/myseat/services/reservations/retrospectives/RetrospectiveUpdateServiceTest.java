package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.RetrospectiveNotFoundException;
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

class RetrospectiveUpdateServiceTest {

    private RetrospectiveUpdateService service;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    private User mockUser;
    private Reservation mockReservation;
    private Retrospective mockRetrospective;

    private final Long USER_ID = 1L;
    private final Long RETROSPECTIVE_ID = 1L;
    private final Long RESERVATION_ID = 1L;

    private final Long ANOTHER_RETROSPECTIVE_ID = 1000L;
    private final Long ANOTHER_RESERVATION_ID = 1000L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RetrospectiveUpdateService(retrospectiveRepository, reservationRepository);

        mockUser = User.builder()
                .id(USER_ID)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        mockReservation = Reservation.builder()
                .id(RESERVATION_ID)
                .user(mockUser)
                .build();

        mockRetrospective = Retrospective.builder()
                .id(RETROSPECTIVE_ID)
                .content("잘했다.")
                .reservation(mockReservation)
                .build();

        given(reservationRepository.findByIdAndUser_Id(RESERVATION_ID,USER_ID)).willReturn(Optional.of(mockReservation));

        given(retrospectiveRepository.findByReservationId(RESERVATION_ID)).willReturn(Optional.of(mockRetrospective));

        given(retrospectiveRepository.findByReservationId(ANOTHER_RETROSPECTIVE_ID))
                .willThrow(new RetrospectiveNotFoundException());
    }

    @Test
    @DisplayName("updateRetrospective 메서드는 retrospective을 반환한다.")
    void updateRetrospective_will_return_retrospective() {
        Retrospective retrospective = service.updateRetrospective(RESERVATION_ID, mockUser, "수정했다.");

        assertThat(retrospective.getContent()).isEqualTo("수정했다.");
        assertThat(retrospective.getId()).isEqualTo(RETROSPECTIVE_ID);
        assertThat(retrospective.getReservation().getId()).isEqualTo(RESERVATION_ID);
    }

    @Test
    @DisplayName("예약자가 아닌 회원이 해당 예약에 대해 회고를 작성하려고 NotOwnedReservationException 예외를 던진다.")
    void NotFound_RetrospectiveId_will_throw_RetrospectiveNotFoundException() {
        assertThatThrownBy(() -> service.updateRetrospective(ANOTHER_RESERVATION_ID, mockUser, "수정했다."))
                .isInstanceOf(NotOwnedReservationException.class);
    }

}
