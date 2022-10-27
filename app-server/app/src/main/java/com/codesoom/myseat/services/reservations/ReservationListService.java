package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 예약 목록 조회 서비스 */
@Service
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

    /**
     * 예약 목록을 조회합니다.
     *
     * @return 모든 유저의 예약 목록
     */
    public Page<Reservation> allReservations(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
