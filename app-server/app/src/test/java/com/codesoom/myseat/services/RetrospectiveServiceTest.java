package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;


class RetrospectiveServiceTest {

    private RetrospectiveService service;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @Mock
    private ReservationRepository reservationRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RetrospectiveService(retrospectiveRepository,reservationRepository);
    }

    @Test
    void will_return_retrospective() {
        Retrospective mockRetrospective = Retrospective.builder().retrospective("잘했다.").build();
        Reservation mockReservation = Reservation.builder()
                .retrospective(mockRetrospective)
                .build();

        given(reservationRepository.findById(1L)).willReturn(Optional.of(mockReservation));

        Retrospective retrospective = service.createRetrospective(1L, mockRetrospective.getRetrospective());

        assertThat(retrospective.getId()).isEqualTo(1L);

    }

}
