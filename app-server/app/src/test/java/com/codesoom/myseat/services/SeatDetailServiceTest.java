package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SeatDetailResponse;
import com.codesoom.myseat.repositories.SeatReservationRepository;
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
    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";

    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 1;

    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    private SeatDetailService service;

    private final SeatReservationRepository reservationRepo
            = mock(SeatReservationRepository.class);

    private User user;
    private Seat seat;
    private SeatReservation seatReservation;

    @BeforeEach
    void setUp() {
        service = new SeatDetailService(reservationRepo);

        user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        seat = Seat.builder()
                .id(SEAT_ID)
                .number(SEAT_NUMBER)
                .isReserved(false)
                .build();

        seatReservation = SeatReservation.builder()
                .id(SEAT_RESERVATION_ID)
                .date(DATE)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .canceled(false)
                .user(user)
                .seat(seat)
                .build();
    }

    @Nested
    @DisplayName("seatDetail 메서드는")
    class Describe_seatReservations_method {
        @BeforeEach
        void setUp() {
            given(reservationRepo.findByDateAndSeatNumber(
                    any(String.class), 
                    eq(SEAT_NUMBER))
            ).willReturn(Optional.of(seatReservation));
        }

        @Nested
        @DisplayName("좌석의 당일 예약 정보를 반환한다.")
        class It_returns_seat_reservation_data_of_today {
            SeatDetailResponse subject() {
                return service.seatDetail(SEAT_NUMBER, user);
            }

            @Test
            void test() {
                SeatDetailResponse seatDetail = subject();

                assertThat(seatDetail.getSeatNumber()).isEqualTo(SEAT_NUMBER);
                assertThat(seatDetail.getDate()).isEqualTo(DATE);
                assertThat(seatDetail.getCheckIn()).isEqualTo(CHECK_IN);
                assertThat(seatDetail.getCheckOut()).isEqualTo(CHECK_OUT);
                assertThat(seatDetail.getUserName()).isEqualTo(NAME);
            }
        }
    }
}
