package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationCancelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SeatReservationCancelServiceTest {
    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 3;
    private static final String USER_NAME = "코드숨";
    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    private SeatReservationCancelService service;

    private final SeatRepository seatRepository
            = mock(SeatRepository.class);
    private final SeatReservationRepository reservationRepository
            = mock(SeatReservationRepository.class);

    private SeatReservationCancelRequest request;
    private Seat seat;
    private SeatReservation reservation;

    @BeforeEach
    void setUp() {
        service = new SeatReservationCancelService(seatRepository, reservationRepository);
    }

    @Nested
    @DisplayName("cancelReservation 메서드는")
    class Describe_cancelReservation_method {
        @BeforeEach
        void setUp() {
            request = SeatReservationCancelRequest.builder()
                    .userName(USER_NAME)
                    .build();

            seat = Seat.builder()
                    .id(SEAT_ID)
                    .number(SEAT_NUMBER)
                    .isReserved(true)
                    .build();

            reservation = SeatReservation.builder()
                    .id(SEAT_RESERVATION_ID)
                    .seatNumber(SEAT_NUMBER)
                    .userName(USER_NAME)
                    .date(DATE)
                    .checkIn(CHECK_IN)
                    .checkOut(CHECK_OUT)
                    .build();

            given(seatRepository.findByNumber(eq(SEAT_NUMBER)))
                    .willReturn(Optional.of(seat));

            given(reservationRepository.findByDateAndUserName(DATE, USER_NAME))
                    .willReturn(reservation);
        }

        @Nested
        @DisplayName("취소된 예약 정보를 반환한다")
        class It_returns_seat_reservation_data_that_canceled {
            SeatReservation subject() {
                return service.cancelReservation(SEAT_NUMBER, request);
            }

            @Test
            void test() {
                SeatReservation reservation = subject();
                assertThat(reservation.getId()).isEqualTo(SEAT_RESERVATION_ID);
                assertThat(reservation.getSeatNumber()).isEqualTo(SEAT_NUMBER);
                assertThat(reservation.getUserName()).isEqualTo(USER_NAME);
                assertThat(reservation.getDate()).isEqualTo(DATE);
                assertThat(reservation.getCheckIn()).isEqualTo(CHECK_IN);
                assertThat(reservation.getCheckOut()).isEqualTo(CHECK_OUT);

                verify(reservationRepository).delete(any(SeatReservation.class));
            }
        }
    }
}
