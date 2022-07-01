package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 좌석 추가 응답 정보
 */
@Getter
@Builder
@AllArgsConstructor
public class SeatAddResponse {
    private int number;
}
