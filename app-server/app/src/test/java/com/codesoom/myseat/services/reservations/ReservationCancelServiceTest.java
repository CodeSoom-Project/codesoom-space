package com.codesoom.myseat.services.reservations;

import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.enums.ReservationStatus;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReservationCancelService 클래스")
class ReservationCancelServiceTest {

    @InjectMocks
    private ReservationCancelService service;

    @Mock
    private ReservationRepository repository;

    private final Long USER_ID = 1L;

    @DisplayName("cancelReservation 메소드는")
    @Nested
    class Describe_cancel_reservation {
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

            @DisplayName("요청한 회원 소유의 예약이면")
            @Nested
            class Context_with_own_reservation{
                @DisplayName("성공적으로 예약을 취소한다")
                @Test
                void will_cancel_successfully() {
                    service.cancelReservation(USER_ID, EXIST_ID);
                    assertThat(RESERVATION.getStatus()).isEqualTo(ReservationStatus.CANCELED);
                }
            }

            @DisplayName("요청한 회원이 소유한 예약이 아니면")
            @Nested
            class Context_with_not_own_reservation {
                @DisplayName("예외를 던진다")
                @Test
                void will_throw_exception() {
                    Long NOT_OWNED_USER_ID = 888L;
                    assertThrows(NotOwnedReservationException.class,
                            () -> service.cancelReservation(NOT_OWNED_USER_ID, EXIST_ID));
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
                given(repository.findById(same(NOT_EXIST_ID)))
                        .willReturn(Optional.empty());

                assertThrows(ReservationNotFoundException.class,
                        () -> service.cancelReservation(USER_ID, NOT_EXIST_ID));
            }
        }
    }

}
