package com.codesoom.myseat.services.admin;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** 관리자 회원 예약 목록 조회 서비스 */
@Service
public class AdminListService {

    private final ReservationRepository reservationRepository;

    /**
     * 회원 예약 목록을 조회합니다.
     *
     * @return 회원의 모든 예약 목록
     */
    public AdminListService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> reservations() {
        return reservationRepository.findAll();
    }
}
