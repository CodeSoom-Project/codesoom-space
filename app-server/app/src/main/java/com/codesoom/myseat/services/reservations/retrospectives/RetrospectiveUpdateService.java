package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RetrospectiveUpdateService {

    private final ReservationRepository reservationRepository;

    public RetrospectiveUpdateService(
            ReservationRepository reservationRepository
    ) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 회고 내용을 수정합니다.
     *
     * @param reservationId 예약 Id
     * @param user 회원
     * @param content 수정 할 회고 내용
     * @throws NotOwnedReservationException 회원이 해당 예약을 소유하지 않는 경우
     */
    @Transactional
    public void update(
            final Long reservationId, 
            final User user, 
            final String content
    ) {
        Reservation reservation 
                = reservationRepository.findByIdAndUser_Id(reservationId, user.getId())
                .orElseThrow(NotOwnedReservationException::new);

        reservation.updateRetrospective(content);
    }

}
