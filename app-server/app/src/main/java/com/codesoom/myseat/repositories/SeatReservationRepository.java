package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 좌석 예약 레포지토리
 */
public interface SeatReservationRepository
        extends JpaRepository<SeatReservation, Long> {
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
     * 날짜와 회원 이메일로 좌석 예약 정보를 찾는다.
     *
     * @param today 오늘 날짜
     * @param email 회원 이메일
     * @return 좌석 예약 정보
     */
    Optional<SeatReservation> findByDateAndUser_Email(String today, String email);

    /**
     * 좌석 예약 정보를 삭제한다.
     *
     * @param s must not be {@literal null}. 삭제할 좌석 예약 정보
     */
    void delete(SeatReservation s);

    /**
     * 특정 좌석의 당일 예약 정보를 반환한다.
     *
     * @param today 오늘 날짜
     * @param number 좌석 번호
     * @return 좌석 예약 정보
     */
    Optional<SeatReservation> findByDateAndSeatNumber(String today, int number);

    Optional<SeatReservation> findByDateAndUser_EmailAndCanceledIsFalse(String today, String email);
}
