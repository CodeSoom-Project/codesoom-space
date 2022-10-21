package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.RetrospectiveNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RetrospectiveUpdateService {

    private final RetrospectiveRepository retrospectiveRepository;
    private final ReservationRepository reservationRepository;

    public RetrospectiveUpdateService(RetrospectiveRepository retrospectiveRepository, ReservationRepository reservationRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * 회고 내용을 수정합니다.
     *
     * @param reservationId 예약 Id
     * @param user 회원
     * @param content 수정 할 회고 내용
     * @throws NotOwnedReservationException 요청한 회원이 해당 예약에 대해 회고를 작성하는 경우
     * @throws RetrospectiveNotFoundException 회고 Id를 찾지 못한 경우
     */
    @Transactional
    public Retrospective updateRetrospective(final Long reservationId,
                                             final User user,
                                             final String content) {
        Reservation reservation = reservationRepository.findByIdAndUser_Id(reservationId, user.getId())
                .orElseThrow(NotOwnedReservationException::new);

        Retrospective retrospective = retrospectiveRepository.findByReservationId(reservation.getId())
                .orElseThrow(RetrospectiveNotFoundException::new);

        retrospective.updateContent(content);

        return retrospective;
    }

}
