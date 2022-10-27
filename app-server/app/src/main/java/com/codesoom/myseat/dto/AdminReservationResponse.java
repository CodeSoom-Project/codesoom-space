package com.codesoom.myseat.dto;

import com.codesoom.myseat.domain.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 예약 조회 응답 정보
 */
@Getter
@NoArgsConstructor
public class AdminReservationResponse {

    private Long id;
    private String date;
    private String content;
    private String status;
    private UserResponse user;

    public AdminReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.date = reservation.getDate().getDate();
        this.content = reservation.getPlan().getContent();
        this.status = reservation.getStatus().name();
        this.user = new UserResponse(reservation.getUser());
    }

}
