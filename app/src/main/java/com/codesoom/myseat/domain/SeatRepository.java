package com.codesoom.myseat.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SeatRepository
        extends CrudRepository<Seat, Long> {
    /**
     * 조회된 좌석을 반환한다.
     *
     * @param number 좌석 번호
     * @return 좌석
     */
    Optional<Seat> findByNumber(int number);
}
