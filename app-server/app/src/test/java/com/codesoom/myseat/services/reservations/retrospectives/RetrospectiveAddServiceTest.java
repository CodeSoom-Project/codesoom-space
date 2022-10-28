package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.codesoom.myseat.enums.ReservationStatus.RETROSPECTIVE_COMPLETE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class RetrospectiveAddServiceTest {

    private RetrospectiveAddService service;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    private LocalDateTime NOW = LocalDateTime.of(2022, 10, 26, 0, 0, 0);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RetrospectiveAddService(
                retrospectiveRepository,
                reservationRepository
        );
    }

    @Test
    @DisplayName("createRetrospective 메서드는 retrospective을 반환한다.")
    void will_return_retrospective() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        Reservation mockReservation = Reservation.builder()
                .id(1L)
                .user(mockUser)
                .build();

        Retrospective mockRetrospective = Retrospective.builder()
                .content("잘했다.")
                .reservation(mockReservation)
                .createdDate(NOW)
                .build();

        given(reservationRepository.findById(1L))
                .willReturn(Optional.of(mockReservation));
        given(retrospectiveRepository.save(any()))
                .willReturn(mockRetrospective);
        given(reservationRepository.
                findByIdAndUser_Id(mockReservation.getId(), mockUser.getId()))
                .willReturn(Optional.of(mockReservation));

        Retrospective retrospective =
                service.create(mockUser, 1L, "잘했다.");

        assertThat(retrospective.getContent()).isEqualTo("잘했다.");
        assertThat(retrospective.getCreatedDate()).isEqualTo(NOW);
    }

    @Test
    @DisplayName("예약자가 아닌 회원이 해당 예약에 대해 회고를 작성하려고 한다면 NotRegisteredReservation 예외를 던진다.")
    void If_Unreserved_User_Write_Retrospective_Given_It_throws_NotRegisteredReservation() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        given(reservationRepository.existsByIdAndUser_Id(1000L, mockUser.getId()))
                .willReturn(false);

        assertThatThrownBy(() -> service.create(mockUser, 1000L, "잘했다."))
                .isInstanceOf(NotOwnedReservationException.class);
    }

    @Test
    @DisplayName("회고 작성이 완료가 되면 RETROSPECTIVE_COMPLETE으로 반환한다.")
    void status_return_RETROSPECTIVE_COMPLETE() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        Reservation mockReservation = Reservation.builder()
                .id(1L)
                .user(mockUser)
                .build();

        Retrospective mockRetrospective = Retrospective.builder()
                .content("잘했다.")
                .reservation(mockReservation)
                .createdDate(NOW)
                .build();

        given(reservationRepository.findById(1L))
                .willReturn(Optional.of(mockReservation));
        given(retrospectiveRepository.save(any()))
                .willReturn(mockRetrospective);
        given(reservationRepository.
                findByIdAndUser_Id(mockReservation.getId(), mockUser.getId()))
                .willReturn(Optional.of(mockReservation));

        Retrospective retrospective =
                service.create(mockUser, 1L, "잘했다.");

        assertThat(retrospective.getReservation().getStatus())
                .isEqualTo(RETROSPECTIVE_COMPLETE);
        assertThat(retrospective.getCreatedDate()).isEqualTo(NOW);
    }

}
