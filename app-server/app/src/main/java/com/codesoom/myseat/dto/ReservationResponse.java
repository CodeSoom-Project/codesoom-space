package com.codesoom.myseat.dto;

import com.codesoom.myseat.domain.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 예약 조회 응답 정보 */
@Getter
@NoArgsConstructor
public class ReservationResponse {

    private Long id;
    
    private String date;
    
    private String content;
    
    private String status;

    public ReservationResponse(
            Reservation reservation
    ) {
        this.id = reservation.getId();
        this.date = reservation.getDate().getDate();
        this.content = reservation.getPlan().getContent();
        this.status = reservation.getStatus().name();
    }

}
