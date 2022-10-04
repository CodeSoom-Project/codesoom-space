package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.repositories.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatListServiceTest {
    private SeatListService service;

    private final SeatRepository seatRepo
            = mock(SeatRepository.class);

    @BeforeEach
    void setUp() {
        service = new SeatListService(seatRepo);
    }

    @Test
    void test() {
        // given
        Seat seat1 = Seat.builder()
                .id(1L)
                .number(1)
                .status(false)
                .build();
        
        Seat seat2 = Seat.builder()
                .id(2L)
                .number(2)
                .status(true)
                .build();
        
        List<Seat> givenSeats = new ArrayList<>();
        givenSeats.add(seat1);
        givenSeats.add(seat2);

        given(seatRepo.findAll())
                .willReturn(givenSeats);
        
        // when
        List<Seat> seats = service.seats();

        // then
        assertThat(seats.get(0).getId()).isEqualTo(1L);
        assertThat(seats.get(0).getNumber()).isEqualTo(1);
        assertThat(seats.get(0).getStatus()).isEqualTo(false);
        
        assertThat(seats.get(1).getId()).isEqualTo(2L);
        assertThat(seats.get(1).getNumber()).isEqualTo(2);
        assertThat(seats.get(1).getStatus()).isEqualTo(true);
    }
}
