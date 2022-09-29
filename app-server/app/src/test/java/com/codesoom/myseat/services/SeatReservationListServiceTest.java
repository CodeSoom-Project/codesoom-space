package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatReservationListServiceTest {
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

    private SeatReservationListService service;

    private final SeatReservationRepository reservationRepo
            = mock(SeatReservationRepository.class);

    private User user;
    private Seat seat;
    private SeatReservation seatReservation;

    @BeforeEach
    void setUp() {
        service = new SeatReservationListService(reservationRepo);

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
    @DisplayName("seatReservations 메서드는")
    class Describe_seatReservations_method {
        @BeforeEach
        void setUp() {
            given(reservationRepo.findAllByDate(any(String.class)))
                    .willReturn(Optional.of(List.of(seatReservation)));
        }

        @Nested
        @DisplayName("오늘의 좌석 예약 목록을 반환한다")
        class It_returns_seat_reservation_list_of_today {
            List<SeatReservation> subject() {
                return service.seatReservations();
            }

            @Test
            void test() {
                List<SeatReservation> seatReservations = subject();

                assertThat(seatReservations.get(0).getId()).isEqualTo(SEAT_RESERVATION_ID);
                assertThat(seatReservations.get(0).getDate()).isEqualTo(DATE);
                assertThat(seatReservations.get(0).getCheckIn()).isEqualTo(CHECK_IN);
                assertThat(seatReservations.get(0).getCheckOut()).isEqualTo(CHECK_OUT);
                assertThat(seatReservations.get(0).getCanceled()).isEqualTo(false);
            }
        }
    }
}
