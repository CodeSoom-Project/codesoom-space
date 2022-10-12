package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.enums.ReservationStatus;
import com.codesoom.myseat.repositories.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatReservationServiceTest {
    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String ENCODED_PASSWORD
            = "$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW";
    
    private static final Long SEAT_ID = 1L;
    private static final int NUMBER = 1;

    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private SeatReservationService service;

    private final SeatRepository seatRepo
            = mock(SeatRepository.class);
    
    private final SeatReservationRepository reservationRepo
            = mock(SeatReservationRepository.class);

    @BeforeEach
    void setUp() {
        service = new SeatReservationService(
                seatRepo,
                reservationRepo
        );
    }

    @Test
    @DisplayName("예약 생성 성공")
    void create_reservation_success() {
        // given
        User user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(ENCODED_PASSWORD)
                .build();

        Seat seat = Seat.builder()
                .id(SEAT_ID)
                .number(NUMBER)
                .user(user)
                .build();

        given(seatRepo.save(any(Seat.class)))
                .will(invocation -> Seat.builder()
                        .id(SEAT_ID)
                        .number(NUMBER)
                        .build());

        given(reservationRepo.save(any(SeatReservation.class)))
                .will(invocation -> SeatReservation.builder()
                        .id(SEAT_RESERVATION_ID)
                        .date(DATE)
                        .user(user)
                        .build());

        // when
        SeatReservation seatReservation = service.createReservation(user, seat);

        // then
        assertThat(seatReservation.getId()).isEqualTo(SEAT_RESERVATION_ID);
        assertThat(seatReservation.getDate()).isEqualTo(DATE);
        assertThat(seatReservation.getUser().getId()).isEqualTo(USER_ID);
        assertThat(seatReservation.getUser().getName()).isEqualTo(NAME);
        assertThat(seatReservation.getUser().getEmail()).isEqualTo(EMAIL);
        assertThat(seatReservation.getUser().getPassword()).isEqualTo(ENCODED_PASSWORD);
        assertThat(seatReservation.getStatus()).isEqualTo(ReservationStatus.RESERVED);
    }
}
