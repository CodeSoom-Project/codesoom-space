package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
     * 방문 일자와 회원 아이디가 일치하는 예약 내역을 반환합니다.
     * 
     * @param date 방문 일자
     * @param id 회원 아이디
     * @return 예약 내역
     */
    @Query("SELECT s FROM SeatReservation s WHERE s.date = :date and s.user.id = :userid")
    SeatReservation findReservationByDateAndUserId(@Param("date") String date, @Param("userid") Long id);
}
