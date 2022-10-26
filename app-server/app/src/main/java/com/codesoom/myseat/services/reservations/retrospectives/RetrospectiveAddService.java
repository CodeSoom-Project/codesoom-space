package com.codesoom.myseat.services.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.ContentTooLongException;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetrospectiveAddService {

    private final RetrospectiveRepository retrospectiveRepository;
    private final ReservationRepository reservationRepository;

    public RetrospectiveAddService(
            RetrospectiveRepository retrospectiveRepository,
            ReservationRepository reservationRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * 생성된 회고 내역을 반환합니다.
     *
     * @param user 회원
     * @param reservationId 예약 Id
     * @param content 회고 내용
     * @return 생성된 회고
     * @throws NotOwnedReservationException 예약자가 아닌 회원이 해당 예약에 대해 회고를 작성하려고 한다면 예외를 던집니다.
     * @throws ContentTooLongException 회고의 길이가 너무 길면 던집니다.
     */
    public Retrospective createRetrospective(final User user,
                                             final Long reservationId,
                                             final String content) {
        if (!isReservedUser(reservationId, user.getId())) {
            throw new NotOwnedReservationException();
        }
        log.info("회고 요청: " + content);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        reservation.completeRetrospective();

        Retrospective retrospective = Retrospective.builder()
                .reservation(reservation)
                .content(content)
                .build();

        log.info("회고 요청: " + retrospective.getContent());

        try {
            return retrospectiveRepository.save(retrospective);
        } catch (InvalidDataAccessResourceUsageException e) {
            e.printStackTrace();
            throw new ContentTooLongException();
        }
    }

    /**
     * 예약한 회원이면 true, 그렇지 않으면 false를 반환합니다.
     *
     * @param reservationId 예약 id
     * @param userId 회원 id
     * @return 예약한 회원이면 true, 그렇지 않으면 false
     */
    public boolean isReservedUser(final Long reservationId,
                                  final Long userId) {
        return reservationRepository.existsByIdAndUser_Id(reservationId, userId);
    }

}
