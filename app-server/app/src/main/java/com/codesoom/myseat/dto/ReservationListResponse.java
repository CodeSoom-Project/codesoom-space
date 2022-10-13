package com.codesoom.myseat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/** 예약 목록 조회 응답 정보 */
@NoArgsConstructor
@Getter
public class ReservationListResponse {

    private List<ReservationResponse> reservations;

    public ReservationListResponse(List<ReservationResponse> reservations) {
        this.reservations = reservations;
    }

}
