package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좌석 추가 요청 정보
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatAddRequest {
    private int number;
}
