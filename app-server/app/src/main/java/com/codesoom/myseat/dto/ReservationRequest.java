package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 예약 요청 정보
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private String date;
    
    private String content;
}
