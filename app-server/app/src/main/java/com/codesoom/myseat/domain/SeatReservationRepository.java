package com.codesoom.myseat.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

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

    /**
     * 오늘의 좌석 예약 목록을 반환한다.
     *
     * @return 좌석 예약 목록
     */
    List<SeatReservation> findAllByDate(String today);
}
