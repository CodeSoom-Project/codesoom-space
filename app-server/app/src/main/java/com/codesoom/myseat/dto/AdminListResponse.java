package com.codesoom.myseat.dto;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.enums.ReservationStatus;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AdminListResponse {

    private Long reservation_id;

    private String user_name;

    private Date reservation_date;

    private ReservationStatus content_status;

    public AdminListResponse(final Reservation reservation) {
        this.reservation_id = reservation.getId();
        this.user_name = reservation.getUser().getName();
        this.reservation_date = reservation.getDate();
        this.content_status = reservation.getStatus();
    }

    public static List<AdminListResponse> of(final List<Reservation> reservations) {
        return reservations.stream()
                .map(o -> new AdminListResponse(o))
                .collect(Collectors.toList());
    }
}
