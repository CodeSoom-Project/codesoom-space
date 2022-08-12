package com.codesoom.myseat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SeatRepository
        extends JpaRepository<Seat, Long> {
    /**
     * 조회된 좌석을 반환한다.
     *
     * @param number 좌석 번호
     * @return 좌석
     */
    Optional<Seat> findByNumber(int number);

    /**
     * 좌석을 저장한다.
     * 
     * @param seat must not be {@literal null}. 좌석
     * @return 좌석
     */
    Seat save(Seat seat);

    /**
     * 좌석 목록을 반환한다.
     * 
     * @return 좌석 목록
     */
    List<Seat> findAll();

    @Query("UPDATE Seat SET isReserved=false WHERE isReserved is true")
    @Modifying
    @Transactional
    void seatReservationReset();
}
