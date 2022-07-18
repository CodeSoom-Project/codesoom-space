package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
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

class SeatDetailServiceTest {
    private static final int SEAT_NUMBER = 3;
    private static final String USER_NAME = "코드숨";
    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    private SeatDetailService service;

    private final SeatReservationRepository repository
            = mock(SeatReservationRepository.class);

    private SeatReservation seatReservation;

    @BeforeEach
    void setUp() {
        service = new SeatDetailService(repository);

        seatReservation = SeatReservation.builder()
                .id(SEAT_RESERVATION_ID)
                .seatNumber(SEAT_NUMBER)
                .userName(USER_NAME)
                .date(DATE)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .build();
    }

    @Nested
    @DisplayName("seatDetail 메서드는")
    class Describe_seatReservations_method {
        @BeforeEach
        void setUp() {
            given(repository.findByDateAndSeatNumber(any(String.class), eq(SEAT_NUMBER)))
                    .willReturn(Optional.of(seatReservation));
        }

        @Nested
        @DisplayName("좌석의 당일 예약 정보를 반환한다.")
        class It_returns_seat_reservation_data_of_today {
            SeatReservation subject() {
                return service.seatDetail(SEAT_NUMBER);
            }

            @Test
            void test() {
                SeatReservation seatReservation = subject();

                assertThat(seatReservation.getId()).isEqualTo(SEAT_RESERVATION_ID);
                assertThat(seatReservation.getSeatNumber()).isEqualTo(SEAT_NUMBER);
                assertThat(seatReservation.getUserName()).isEqualTo(USER_NAME);
                assertThat(seatReservation.getDate()).isEqualTo(DATE);
                assertThat(seatReservation.getCheckIn()).isEqualTo(CHECK_IN);
                assertThat(seatReservation.getCheckOut()).isEqualTo(CHECK_OUT);
            }
        }
    }
}
