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
}
