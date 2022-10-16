package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import com.codesoom.myseat.repositories.RetrospectiveRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class RetrospectiveServiceTest {

    private RetrospectiveService service;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RetrospectiveService(retrospectiveRepository, reservationRepository);
    }

    @Test
    @DisplayName("createRetrospective 메서드는 retrospective을 반환한다.")
    void will_return_retrospective() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        Reservation mockReservation = Reservation.builder()
                .id(1L)
                .user(mockUser)
                .build();

        Retrospective mockRetrospective = Retrospective.builder()
                .content("잘했다.")
                .reservation(mockReservation)
                .build();

        given(reservationRepository.findById(1L)).willReturn(Optional.of(mockReservation));
        given(reservationRepository.existsByIdAndUser_Id(1L, mockUser.getId()))
                .willReturn(true);
        given(retrospectiveRepository.save(any())).willReturn(mockRetrospective);

        Retrospective retrospective = service.createRetrospective(mockUser, 1L, "잘했다.");

        assertThat(retrospective.getContent()).isEqualTo("잘했다.");

    }

    @Test
    @DisplayName("UserId와ReservationId가 동일하지 않으면 UserNotFoundException을 던진다.")
    void If_UserId_And_ReservationId_Is_Not_Collect_Given_It_throws_UserNotFoundException() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        given(reservationRepository.existsByIdAndUser_Id(1L, mockUser.getId()))
                .willReturn(true);

        assertThatThrownBy(() -> service.createRetrospective(mockUser, 2L, "잘했다."))
                .isInstanceOf(UserNotFoundException.class);
    }

}
