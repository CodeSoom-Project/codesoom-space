package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.PlanRepository;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;

class ReservationQueryServiceTest {

    private ReservationQueryService service;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PlanRepository planRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new ReservationQueryService(reservationRepository);
    }

    @DisplayName("예약 목록 조회")
    @Test
    void will_return_reservations() {
        //given
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        Plan plan = Plan.builder().content("공부").build();
        Reservation reservation = Reservation.builder()
                .user(mockUser)
                .plan(plan)
                .date("2022-10-12")
                .build();
        plan.addReservation(reservation);
        planRepository.save(plan);
        reservationRepository.save(reservation);
        given(reservationRepository.findAllByUser_Id(same(1L)))
                .willReturn(List.of(reservation));

        //when
        List<Reservation> reservations = service.reservations(mockUser.getId());

        //then
        assertThat(reservations.size()).isEqualTo(1);
        assertThat(reservations.get(0).getUser().getId()).isEqualTo(mockUser.getId());
    }

}
