package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RetrospectiveService {

    private RetrospectiveRepository retrospectiveRepository;
    private ReservationRepository reservationRepository;

    public void createRetrospective(Long id, RetrospectiveRequest request) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        Retrospective retrospective = Retrospective.builder()
                .reservation(reservation.get())
                .retrospective(request.getRetrospective())
                .build();

        retrospectiveRepository.save(retrospective);
    }

}
