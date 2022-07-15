package com.codesoom.myseat.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

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
    Optional<List<SeatReservation>> findAllByDate(String today);

    /**
     * 날짜와 회원 이름으로 좌석 예약 정보를 찾는다.
     *
     * @param today 오늘 날짜
     * @param userName 회원 이름
     * @return 좌석 예약 정보
     */
    Optional<SeatReservation> findByDateAndUserName(String today, String userName);

    /**
     * 좌석 예약 정보를 삭제한다.
     *
     * @param s must not be {@literal null}. 삭제할 좌석 예약 정보
     */
    void delete(SeatReservation s);
}
