package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatListServiceTest {
    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 1;
    private static final String USER_NAME = "코드숨";

    private SeatListService service;

    private final SeatRepository repository
            = mock(SeatRepository.class);

    private Seat seat;

    @BeforeEach
    void setUp() {
        service = new SeatListService(repository);

        seat = Seat.builder()
                .id(SEAT_ID)
                .number(SEAT_NUMBER)
                .userName(USER_NAME)
                .build();
    }

    @Nested
    @DisplayName("seats 메서드는")
    class Describe_seats_method {
        @BeforeEach
        void setUp() {
            given(repository.findAll()).willReturn(List.of(seat));
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
                assertThat(seats.get(0).getUserName()).isEqualTo(USER_NAME);
            }
        }
    }
}
