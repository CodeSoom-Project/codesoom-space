package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 좌석 목록 조회 응답 정보
 */
@Getter
@Builder
@AllArgsConstructor
public class SeatListResponse {
    private int number;

    private String status;
}
