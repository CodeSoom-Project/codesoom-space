package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 예약 상세 조회 서비스 */
@Slf4j
@Service
public class ReservationDetailService {

    private final ReservationRepository repository;

    public ReservationDetailService(ReservationRepository repository) {
        this.repository = repository;
    }

    /**
     * 주어진 user id와 reservation id로 예약 내용을 상세 조회합니다.
     *
     * @param userId        유저 아이디(PK)
     * @param reservationId 예약 아이디
     * @return 주어진 정보로 조회한 예약 정보
     * @throws ReservationNotFoundException 예약 정보를 찾지 못한 경우 던짐
     */
    @Transactional(readOnly = true)
    public Reservation reservationOfUser(Long reservationId, Long userId) {
        return repository.findByIdAndUser_Id(reservationId, userId)
                .orElseThrow(ReservationNotFoundException::new);
    }

}
