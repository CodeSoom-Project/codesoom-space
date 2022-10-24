package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.ReservationRequest;
import com.codesoom.myseat.enums.ReservationStatus;
import com.codesoom.myseat.exceptions.CannotUpdateCanceledReservationException;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReservationUpdateService 클래스")
class ReservationUpdateServiceTest {

    @InjectMocks
    private ReservationUpdateService service;

    @Mock
    private ReservationRepository repository;

    private final Long USER_ID = 1L;

    @DisplayName("updateReservation 메소드는")
    @Nested
    class Describe_update_reservation {

        @DisplayName("주어진 예약 id로 예약을 찾을 수 있고")
        @Nested
        class Context_with_exist_reservation {

            private final Long EXIST_ID = 99L;
            private final User USER = User.builder()
                    .id(USER_ID)
                    .name("김철수")
                    .email("soo@email.com")
                    .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                    .build();
            private final Plan PLAN = Plan.builder().content("공부").build();
            private Reservation RESERVATION;

            @DisplayName("요청한 회원 소유의 예약이면")
            @Nested
            class Context_with_own_reservation{
                @BeforeEach
                void setUp() {
                    RESERVATION = Reservation.builder()
                            .user(USER)
                            .date("2022-10-17")
                            .plan(PLAN)
                            .build();
                    given(repository.findById(same(EXIST_ID)))
                            .willReturn(Optional.of(RESERVATION));
                }
                
                @DisplayName("성공적으로 예약을 수정한다")
                @Test
                void will_update_successfully() {
                    //given
                    ReservationRequest request = new ReservationRequest("2022-10-18", "수정 데이터");

                    //when
                    service.updateReservation(USER_ID, EXIST_ID, request);

                    //then
                    assertThat(RESERVATION.getPlan().getContent()).isEqualTo(request.getContent());
                    assertThat(RESERVATION.getDate()).isEqualTo(request.getDate());
                }
            }

            @DisplayName("요청한 회원이 소유한 예약이 아니면")
            @Nested
            class Context_with_not_own_reservation{
                @BeforeEach
                void setUp() {
                    RESERVATION = Reservation.builder()
                            .user(USER)
                            .date("2022-10-17")
                            .plan(PLAN)
                            .build();
                    given(repository.findById(same(EXIST_ID)))
                            .willReturn(Optional.of(RESERVATION));
                }
                
                @DisplayName("예외를 던진다")
                @Test
                void will_throw_exception() {
                    //given
                    Long NOT_OWNED_USER_ID = 888L;
                    ReservationRequest request = new ReservationRequest("2022-10-18", "수정 데이터");

                    //when
                    assertThrows(NotOwnedReservationException.class,
                            () -> service.updateReservation(NOT_OWNED_USER_ID, EXIST_ID, request));
                }
            }

            @DisplayName("취소된 예약이면")
            @Nested
            class Context_with_canceled_reservation{
                @BeforeEach
                void setUp() {
                    RESERVATION = Reservation.builder()
                            .user(USER)
                            .date("2022-10-17")
                            .plan(PLAN)
                            .status(ReservationStatus.CANCELED)
                            .build();
                    given(repository.findById(same(1L)))
                            .willReturn(Optional.of(RESERVATION));
                }

                @DisplayName("CannotUpdateCanceledReservationException을 던진다.")
                @Test
                void will_throw_cannot_update_canceled_reservation_exception() {
                    //given
                    ReservationRequest request
                            = new ReservationRequest("2022-10-18", "수정 데이터");

                    //when
                    assertThrows(CannotUpdateCanceledReservationException.class,
                            () -> service.updateReservation(USER_ID, 1L, request));
                }
            }
        }

        @DisplayName("주어진 예약 id로 예약을 찾지 못하면")
        @Nested
        class Context_with_not_exist_reservation {

            private final Long NOT_EXIST_ID = 999L;

            @DisplayName("not found 예외를 던진다")
            @Test
            void will_throw_reservation_not_found_exception() {
                //given
                ReservationRequest request = new ReservationRequest("2022-10-18", "수정 데이터");
                given(repository.findById(same(NOT_EXIST_ID)))
                        .willReturn(Optional.empty());

                //when & then
                assertThrows(ReservationNotFoundException.class,
                        ()-> service.updateReservation(USER_ID, NOT_EXIST_ID, request));
            }
        }

    }

}
