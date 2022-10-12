package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 좌석 예약 레포지토리
 */
public interface SeatReservationRepository
        extends JpaRepository<SeatReservation, Long> {
    /**
     * 
     * @param s must not be {@literal null}.
     * @return
     */
    SeatReservation save(SeatReservation s);

    /**
     * 좌석 예약 정보를 삭제한다.
     *
     * @param s must not be {@literal null}. 삭제할 좌석 예약 정보
     */
    void delete(SeatReservation s);

    List<SeatReservation> findAll();

    /**
     * 주어진 방문 일자와 회원 id로 예약 내역 검색에 성공하면 true, 그렇지 않으면 false를 반환합니다.
     * 
     * @param date 방문 일자
     * @param id 회원 id
     * @return 주어진 방문 일자와 회원 id로 예약 내역 검색에 성공하면 true, 그렇지 않으면 false
     */
    boolean existsByDateAndUser_Id(String date, Long id);
}
