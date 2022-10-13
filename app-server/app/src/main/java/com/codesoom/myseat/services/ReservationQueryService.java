package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 예약 조회 담당 */
@Slf4j
@Service
public class ReservationQueryService {

    private final ReservationRepository repository;

    public ReservationQueryService(ReservationRepository repository) {
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
        return repository.findAllByUser_Id(userId);
    }

}
