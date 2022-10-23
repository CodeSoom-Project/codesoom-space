package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 예약 목록 조회 서비스 */
public class ReservationListService {
    
    private final ReservationRepository repository;

    public ReservationListService(ReservationRepository repository) {
        this.repository = repository;
    }

    /**
     * 주어진 user id로 예약 목록을 조회합니다.
     *
     * @param userId 유저 아이디(PK)
     * @return 해당 유저의 모든 예약 목록
     */
    @Transactional(readOnly = true)
    public List<Reservation> reservations(Long userId) {
        return repository.findAllByUser_IdOrderByDateDesc(userId);
    }
    
}
