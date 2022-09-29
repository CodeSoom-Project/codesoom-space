package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatListServiceTest {
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

    private SeatListService service;

    private final SeatRepository seatRepo
            = mock(SeatRepository.class);

    private User user;
    private SeatReservation seatReservation;
    private Seat seat;

    @BeforeEach
    void setUp() {
        service = new SeatListService(seatRepo);

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
    @DisplayName("seats 메서드는")
    class Describe_seats_method {
        @BeforeEach
        void setUp() {
            given(seatRepo.findAll())
                    .willReturn(List.of(seat));
        }

        @Nested
        @DisplayName("좌석 목록을 반환한다")
        class It_returns_seat_list {
            List<Seat> subject() {
                return service.seats();
            }

            @Test
            void test() {
                List<Seat> seats = subject();

                assertThat(seats.get(0).getId()).isEqualTo(SEAT_ID);
                assertThat(seats.get(0).getNumber()).isEqualTo(SEAT_NUMBER);
                assertThat(seats.get(0).isReserved()).isEqualTo(false);
            }
        }
    }
}
