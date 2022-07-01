package com.codesoom.myseat.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * 좌석 예약 레포지토리
 */
public interface SeatReservationRepository
        extends CrudRepository<SeatReservation, Long> {
    /**
     * 좌석 예약 정보를 저장한다.
     *
     * @param seatReservation must not be {@literal null}. 저장할 좌석 예약 정보
     * @return 좌석 예약 정보
     */
    SeatReservation save(SeatReservation seatReservation);
}
