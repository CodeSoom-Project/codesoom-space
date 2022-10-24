package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.repositories.PlanRepository;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;

@DisplayName("ReservationDetailService 클래스")
class ReservationDetailServiceTest {

    private ReservationDetailService service;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PlanRepository planRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new ReservationDetailService(reservationRepository);
    }

    @DisplayName("reservation 메소드는")
    @Nested
    class Describe_reservation {

        @DisplayName("찾을 수 있는 예약 id와 회원 id가 주어지면")
        @Nested
        class Context_with_exist_reservation_id_and_user_id {

            User mockUser = User.builder()
                    .id(1L)
                    .name("김철수")
                    .email("soo@email.com")
                    .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                    .build();
            Plan plan = Plan.builder().content("공부").build();

            @DisplayName("예약 상세 조회 결과를 반환한다")
            @Test
            void will_return_reservation() {
                //given
                given(reservationRepository.findByIdAndUser_Id(same(1L), same(1L)))
                        .willReturn(Optional.ofNullable(Reservation.builder()
                                .id(1L)
                                .user(mockUser)
                                .plan(plan)
                                .date(new Date("2022-10-12"))
                                .build()));

                //when
                Reservation reservation = service.reservation(1L, 1L);

                //then
                assertThat(reservation).isNotNull();
                assertThat(reservation.getId()).isEqualTo(1L);
                assertThat(reservation.getUser().getId()).isEqualTo(1L);
            }
        }

        @DisplayName("주어진 예약 id와 회원 id로 예약 정보를 찾을 수 없으면")
        @Nested
        class Context_with_not_exist_id {
            @DisplayName("ReservationNotFoundException을 던진다")
            @Test
            void will_throw_exception() {
                //given
                given(reservationRepository.findByIdAndUser_Id(same(100L), same(100L)))
                        .willThrow(ReservationNotFoundException.class);

                //when & then
                assertThrows(ReservationNotFoundException.class,
                        ()-> service.reservation(100L, 100L));
            }
        }
    }

}
