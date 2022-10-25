package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.dto.ReservationRequest;
import com.codesoom.myseat.exceptions.AlreadyReservedException;
import com.codesoom.myseat.exceptions.CannotUpdateCanceledReservationException;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.NotReservableDateException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 예약 수정 담당 */
@Service
public class ReservationUpdateService {

    private final ReservationRepository repository;

    public ReservationUpdateService(ReservationRepository repository) {
        this.repository = repository;
    }

    /**
     * 예약 내용을 수정합니다.
     *
     * @param userId 수정을 요청한 회원 id
     * @param id 예약 id
     * @param request 수정 데이터
     * @throws ReservationNotFoundException 주어진 예약 id로 예약을 찾지 못한 경우
     * @throws NotOwnedReservationException 요청한 회원 소유의 예약이 아닌 경우
     * @throws AlreadyReservedException 수정하려는 날짜에 예약한 기록이 이미 있는 경우
     * @throws CannotUpdateCanceledReservationException 취소된 예약을 수정하려고 하면 던집니다.
     */
    @Transactional
    public void updateReservation(Long userId, Long id, ReservationRequest request) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);
        Date date = new Date(request.getDate());
        if (!date.isReservable()) {
            throw new NotReservableDateException();
        }
        if(reservation.isCanceled()) {
            throw new CannotUpdateCanceledReservationException();
        }
        if (!reservation.isOwnReservation(userId)) {
            throw new NotOwnedReservationException();
        }
        if (reservation.isDifferentDate(reservation.getDate())) {
            if (hasSameDateReservation(date, userId)) {
                throw new AlreadyReservedException();
            }
        }
        reservation.update(request);
    }

    /**
     * 동일한 날짜에 예약한 기록의 유무를 반환합니다.
     *
     * @param date 예약 날짜
     * @param userId 회원 id
     * @return 기록이 있으면 true, 없으면 false
     */
    private boolean hasSameDateReservation(Date date, Long userId) {
        return repository.existsByDateAndUser_Id(date, userId);
    }

}
