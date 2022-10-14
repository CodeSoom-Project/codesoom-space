package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        service = new RetrospectiveService(retrospectiveRepository, reservationRepository);
    }

    @Test
    void will_return_retrospective() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        Retrospective mockRetrospective = Retrospective.builder().retrospective("잘했다.").build();
        Reservation reservation = Reservation.builder()
                .retrospective(mockRetrospective)
                .user(mockUser)
                .build();

        given(reservationRepository.findById(1L)).willReturn(Optional.of(reservation));
        given(retrospectiveRepository.findById(1L)).willReturn(Optional.of(mockRetrospective));

        Retrospective retrospective = service.createRetrospective(mockUser, 1L, "잘했다.");

        assertThat(retrospective.getRetrospective()).isEqualTo("잘했다.");
        assertThat(reservation.getRetrospective().getRetrospective()).isEqualTo("잘했다.");

    }

    @Test
    void If_UserId_And_ReservationId_Is_Not_Collect_Given_It_throws_UserNotFoundException() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        assertThatThrownBy(() -> service.createRetrospective(mockUser, 2L, "잘했다."))
                .isInstanceOf(UserNotFoundException.class);
    }

}
