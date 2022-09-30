package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.SeatNotFoundException;
import com.codesoom.myseat.repositories.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatReservationCancelServiceTest {
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
    
    private SeatReservationCancelService service;

    private final SeatRepository seatRepo
            = mock(SeatRepository.class);
    
    private final SeatReservationRepository reservationRepo
            = mock(SeatReservationRepository.class);

    private User user;
    private Seat seat;
    private SeatReservation seatReservation;

    @BeforeEach
    void setUp() {
        service = new SeatReservationCancelService(
                seatRepo, 
                reservationRepo
        );
    }

    @Nested
    @DisplayName("cancelReservation 메서드는")
    class Describe_cancelReservation_method {
        @BeforeEach
        void setUp() {
            user = User.builder()
                    .id(USER_ID)
                    .name(NAME)
                    .email(EMAIL)
                    .password(PASSWORD)
                    .build();

            seat = Seat.builder()
                    .id(SEAT_ID)
                    .number(SEAT_NUMBER)
                    .status(false)
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

            given(seatRepo.findByNumber(eq(SEAT_NUMBER)))
                    .willReturn(Optional.of(seat));

            given(reservationRepo.findByDateAndUser_Email(DATE, EMAIL))
                    .willReturn(Optional.of(seatReservation));
        }

        @Nested
        @DisplayName("예약을 취소한다.")
        class It_returns_seat_reservation_data_that_canceled {
            Seat subject() {
                service.cancelReservation(SEAT_NUMBER, user);

                return seatRepo.findByNumber(SEAT_NUMBER)
                        .orElseThrow(() -> new SeatNotFoundException(
                                "[" + SEAT_NUMBER + "]번 좌석을 찾을 수 없어서 조회에 실패했습니다."));
            }

            @Test
            void test() {
                Seat seat = subject();

                assertThat(seat.isStatus()).isEqualTo(false);
            }
        }
    }
}
