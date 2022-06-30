package com.codesoom.myseat.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SeatRepository
        extends CrudRepository<Seat, Long> {
    /**
     * 특정 좌석을 조회한다.
     *
     * @param id must not be {@literal null}. 좌석 id
     * @return 좌석
     */
    Optional<Seat> findById(Long id);
}
