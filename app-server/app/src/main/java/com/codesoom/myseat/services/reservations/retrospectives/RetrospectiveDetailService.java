package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.exceptions.RetrospectiveNotFoundException;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** 회고 상세 조회 서비스 */
@Service
@Slf4j
public class RetrospectiveDetailService {
    private final RetrospectiveRepository retrospectiveRepo;

    public RetrospectiveDetailService(
            RetrospectiveRepository retrospectiveRepo
    ) {
        this.retrospectiveRepo = retrospectiveRepo;
    }

    /**
     * 주어진 예약 id로 조회된 회고를 반환합니다.
     * @param id 예약 id
     * @return 조회된 회고
     * @throw RetrospectiveNotFoundException 회고 조회에 실패하면 던집니다.
     */
    public Retrospective retrospective(
            Long id
    ) {
        return retrospectiveRepo.findByReservationId(id)
                .orElseThrow(() -> new RetrospectiveNotFoundException());
    }
}
