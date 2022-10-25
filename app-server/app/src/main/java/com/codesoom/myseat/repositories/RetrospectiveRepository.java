package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.Retrospective;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetrospectiveRepository 
        extends JpaRepository<Retrospective, Long> {
    
    /**
     * 주어진 예약 id로 조회된 회고를 반환합니다.
     * @param id 예약 id
     * @return 조회된 회고
     */
    Optional<Retrospective> findByReservationId(Long id);
    
}
