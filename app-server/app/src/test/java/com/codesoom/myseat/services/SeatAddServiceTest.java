package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.repositories.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatAddServiceTest {
    private static final Long ID = 1L;
    private static final int NUMBER = 3;

    private SeatAddService service;

    private final SeatRepository seatRepo
            = mock(SeatRepository.class);

    @BeforeEach
    void setUp() {
        service = new SeatAddService(seatRepo);
    }

    @Test
    @DisplayName("좌석 생성 성공")
    void create_seat_success() {
        // given
        given(seatRepo.save(any(Seat.class)))
                .will(invocation -> Seat.builder()
                        .id(ID)
                        .number(NUMBER)
                        .build());

        // when
        Seat seat = service.createSeat(NUMBER);

        // then
        assertThat(seat.getId()).isEqualTo(ID);
        assertThat(seat.getNumber()).isEqualTo(NUMBER);
    }
}
