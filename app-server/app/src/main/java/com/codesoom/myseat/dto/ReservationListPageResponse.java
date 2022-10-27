package com.codesoom.myseat.dto;

import com.codesoom.myseat.domain.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 예약 목록 조회 응답 정보
 */
@NoArgsConstructor
@Getter
public class ReservationListPageResponse {

    private List<AdminReservationResponse> reservations;
    private Pagination pagination;

    public ReservationListPageResponse(Page<Reservation> pageReservation) {
        this.reservations = pageReservation.getContent().stream()
                .map(AdminReservationResponse::new)
                .collect(Collectors.toList());
        this.pagination = new Pagination(
                pageReservation.getNumber(),
                pageReservation.getSize(),
                pageReservation.getTotalPages()
        );
    }

}
