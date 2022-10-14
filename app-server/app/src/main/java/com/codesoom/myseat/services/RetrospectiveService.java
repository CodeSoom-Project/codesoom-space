package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
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
        if(reservationId != user.getId()) {
            throw new UserNotFoundException();
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .build();

        Retrospective retrospective = Retrospective.builder()
                .retrospective(content)
                .reservation(reservation)
                .build();

        retrospective.addReservation(reservation);

        reservationRepository.save(reservation);
        retrospectiveRepository.save(retrospective);

        return retrospective;
    }

}
