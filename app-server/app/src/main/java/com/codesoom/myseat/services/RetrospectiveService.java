package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetrospectiveService {

    private final RetrospectiveRepository retrospectiveRepository;
    private final ReservationRepository reservationRepository;

    public RetrospectiveService(RetrospectiveRepository retrospectiveRepository, ReservationRepository reservationRepository) {
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
     * @throws UserNotFoundException 회원과 예약 동일하지 않으면 던집니다.
     */
    public Retrospective createRetrospective(final User user,
                                             final Long reservationId,
                                             final String content) {
        if (!isValid(reservationId, user.getId())) {
            throw new UserNotFoundException();
        }

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        Retrospective retrospective = Retrospective.builder()
                .reservation(reservation)
                .content(content)
                .build();

        return retrospectiveRepository.save(retrospective);
    }

    public boolean isValid(final Long reservationId,
                           final Long userId) {
        return reservationRepository.existsByIdAndUser_Id(reservationId, userId);
    }
}
