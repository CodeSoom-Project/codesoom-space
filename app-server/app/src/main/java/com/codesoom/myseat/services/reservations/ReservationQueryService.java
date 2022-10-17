package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
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

    /**
     * 주어진 user id와 reservation id로 예약 내용을 상세 조회합니다.
     *
     * @param userId        유저 아이디(PK)
     * @param reservationId 예약 아이디
     * @return 주어진 정보로 조회한 예약 정보
     * @throws ReservationNotFoundException 예약 정보를 찾지 못한 경우 던짐
     */
    @Transactional(readOnly = true)
    public Reservation reservation(Long reservationId, Long userId) {
        return repository.findByIdAndUser_Id(reservationId, userId)
                .orElseThrow(ReservationNotFoundException::new);
    }

}
