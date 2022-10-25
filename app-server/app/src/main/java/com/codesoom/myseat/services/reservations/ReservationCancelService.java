package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** 예약 취소 서비스 */
@Service
@Slf4j
public class ReservationCancelService {

    private final ReservationRepository repository;

    public ReservationCancelService(
            ReservationRepository repository
    ) {
        this.repository = repository;
    }

    /**
     * 주어진 예약 id로 찾은 예약 정보의 상태를 취소로 변경합니다.
     *
     * @param userId 취소 요청한 회원 id
     * @param id 예약 id
     * @throws ReservationNotFoundException 주어진 예약 id로 예약을 찾지 못한 경우
     * @throws NotOwnedReservationException 요청한 회원의 소유한 예약이 아닌 경우
     */
    public void cancelReservation(
            Long userId, 
            Long id
    ) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);
        if (!reservation.isOwnReservation(userId)) {
            throw new NotOwnedReservationException();
        }
        reservation.cancel();
        repository.save(reservation);
    }

}
