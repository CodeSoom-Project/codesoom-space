package com.codesoom.myseat.services.admin;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.enums.ReservationStatus;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class AdminReservationListServiceTest {

    private AdminReservationListService adminReservationListService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        adminReservationListService = new AdminReservationListService(reservationRepository);
    }

    @DisplayName("회원의 모든 예약 목록을 조회한다")
    @Test
    void will_return_reservations() {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .build();
        Plan plan = Plan.builder().content("코테풀기").build();
        Reservation reservation = Reservation.builder()
                .id(1L)
                .user(mockUser)
                .date(new Date("2022-10-12"))
                .status(ReservationStatus.valueOf("RETROSPECTIVE_COMPLETE"))
                .plan(plan)
                .build();

        given(reservationRepository.findAll())
                .willReturn(List.of(reservation));

        List<Reservation> reservations = adminReservationListService.reservations();

        assertThat(reservations.size()).isEqualTo(1);

        assertThat(reservations.get(0).getUser().getName()).isEqualTo("김철수");
        assertThat(reservations.get(0).getDate()).isEqualTo(new Date("2022-10-12"));
        assertThat(reservations.get(0).getStatus()).isEqualTo(ReservationStatus.valueOf("RETROSPECTIVE_COMPLETE"));
    }
}
