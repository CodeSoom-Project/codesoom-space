package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.PlanRepository;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;

@DisplayName("ReservationListService 클래스")
class ReservationListServiceTest {

    private ReservationListService service;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PlanRepository planRepository;

    private User mockUser;
    private Reservation mockReservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new ReservationListService(reservationRepository);
    }

    @BeforeEach()
    void dataSetUp() {
        mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        Plan plan = Plan.builder().content("공부").build();
        mockReservation = Reservation.builder()
                .user(mockUser)
                .plan(plan)
                .date(new Date("2022-10-12"))
                .build();
    }

    @DisplayName("reservations 메소드는")
    @Nested
    class Describe_reservations {

        @DisplayName("주어진 사용자 정보와 일치하는 모든 예약 목록을 조회한다")
        @Test
        void will_return_reservations() {
            given(reservationRepository.findAllByUser_IdOrderByDateDesc(same(1L)))
                    .willReturn(List.of(mockReservation));

            //when
            List<Reservation> reservations = service.reservations(mockUser.getId());

            //then
            assertThat(reservations.size()).isEqualTo(1);
            assertThat(reservations.get(0).getUser().getId()).isEqualTo(mockUser.getId());
        }
    }

    @DisplayName("allReservations 메소드는")
    @Nested
    class Describe_allReseravtions {
        @BeforeEach()
        void setUp() {
            given(reservationRepository.findAll())
                    .willReturn(List.of(mockReservation));
        }

        @DisplayName("모든 예약 목록을 반환한다")
        @Test
        void it_returns_all_reservations() {
            List<Reservation> reservations = service.allReservations();

            assertThat(reservations.size()).isEqualTo(1);
            assertThat(reservations.get(0).getPlan().getContent())
                    .isEqualTo("공부");
        }
    }
}
