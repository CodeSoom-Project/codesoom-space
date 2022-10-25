package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.enums.ReservationStatus;
import com.codesoom.myseat.exceptions.AlreadyReservedException;
import com.codesoom.myseat.repositories.PlanRepository;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

class ReservationAddServiceTest {
    private ReservationAddService service;

    @Mock
    private ReservationRepository reservationRepo;

    @Mock
    private PlanRepository planRepo;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        
        service = new ReservationAddService(planRepo, reservationRepo);
    }
    
    @Test
    @DisplayName("createReservation 메서드는 SeatReservation을 반환한다")
    void createReservation_returns_SeatReservation() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        given(reservationRepo.existsByDateAndUser_IdAndStatusNot(new Date("2022-10-11"), 1L, ReservationStatus.CANCELED))
                .willReturn(false);

        Reservation reservation 
                = service.createReservation(mockUser, "2022-10-11", "책읽기, 코테 풀기");
        
        assertThat(reservation.getUser().getEmail()).isEqualTo("soo@email.com");
        assertThat(reservation.getDate().getDate()).isEqualTo("2022-10-11");
        assertThat(reservation.getPlan().getContent()).isEqualTo("책읽기, 코테 풀기");
        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.RETROSPECTIVE_WAITING);
    }

    @Test
    @DisplayName("이미 예약 했던 방문 일자가 주어지면 AlreadyReservedException를 던진다")
    void If_date_that_already_reserved_given_It_throws_AlreadyReservedException() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        
        Reservation mockReservation = Reservation.builder()
                .id(2L)
                .date(new Date("2022-10-11"))
                .status(ReservationStatus.RETROSPECTIVE_WAITING)
                .user(mockUser)
                .build();

        given(reservationRepo.existsByDateAndUser_IdAndStatusNot(new Date("2022-10-11"), 1L, ReservationStatus.CANCELED))
                .willReturn(true);
        
        given(reservationRepo.findByDateAndUser_Id(new Date("2022-10-11"), 1L))
                .willReturn(Optional.of(mockReservation));
         
        assertThatThrownBy(() -> service.createReservation(mockUser, "2022-10-11", "책읽기, 코테 풀기"))
                .isInstanceOf(AlreadyReservedException.class);
    }
    
    @Test
    @DisplayName("해당 방문 일자의 예약이 존재하지만 취소된 예약이면 생성된 Reservation을 반환한다.")
    void If_date_that_already_reserved_but_canceled_given_It_returns_reservation() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        
        Reservation mockReservation = Reservation.builder()
                .id(2L)
                .date(new Date("2022-10-11"))
                .status(ReservationStatus.CANCELED)
                .user(mockUser)
                .build();
        
        given(reservationRepo.existsByDateAndUser_IdAndStatusNot(new Date("2022-10-11"), 1L, ReservationStatus.CANCELED))
                .willReturn(false);
        
        given(reservationRepo.findByDateAndUser_Id(new Date("2022-10-11"), 1L))
                .willReturn(Optional.of(mockReservation));
        
        Reservation reservation
                = service.createReservation(mockUser, "2022-10-11", "책읽기, 코테 풀기");
        
        assertThat(reservation.getUser().getEmail()).isEqualTo("soo@email.com");
        assertThat(reservation.getDate().getDate()).isEqualTo("2022-10-11");
        assertThat(reservation.getPlan().getContent()).isEqualTo("책읽기, 코테 풀기");
        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.RETROSPECTIVE_WAITING);
    }
}
