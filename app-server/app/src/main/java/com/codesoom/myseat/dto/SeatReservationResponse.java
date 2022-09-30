package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 좌석 예약 응답 정보
 */
@Getter
@Builder
@AllArgsConstructor
public class SeatReservationResponse {
    private String name;

    private int number;

    private String date;

    private String checkIn;

    private String checkOut;
}
