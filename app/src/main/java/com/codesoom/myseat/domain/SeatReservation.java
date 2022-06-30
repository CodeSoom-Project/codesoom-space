package com.codesoom.myseat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SeatReservation {
    private Long id;

    private Long seatId;

    private Long userId;

    private String date;

    private String checkIn;

    private String checkOut;
}
