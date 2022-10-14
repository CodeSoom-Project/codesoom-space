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

    public Retrospective createRetrospective(User user, Long reservationId, String content) {
        if (!isValid(reservationId, user.getId())) {
            throw new UserNotFoundException();
        }

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        Retrospective retrospective = Retrospective.builder()
                .reservation(reservation)
                .retrospective(content)
                .build();

        return retrospectiveRepository.save(retrospective);
    }

    public boolean isValid(Long reservationId, Long userId) {
        return reservationRepository.existsByIdAndUser_Id(reservationId, userId);
    }
}
