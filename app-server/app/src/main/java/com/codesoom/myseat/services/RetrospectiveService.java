package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RetrospectiveService {

    private RetrospectiveRepository retrospectiveRepository;
    private Retrospective retrospective;
    private SeatReservationRepository seatReservationRepository;

    public void createRetrospective(Long id, RetrospectiveRequest request) {
        Optional<SeatReservation> seatReservation = seatReservationRepository.findById(id);

        retrospective = Retrospective.builder()
                .plan(Plan.builder()
                        .seatReservation(seatReservation.get())
                        .build())
                .retrospective(request.getRetrospective())
                .build();

        retrospectiveRepository.save(retrospective);
    }

}
