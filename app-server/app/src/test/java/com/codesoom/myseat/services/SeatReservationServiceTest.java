package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.repositories.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.exceptions.UserAlreadyReservedSeatTodayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatReservationServiceTest {
    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";

    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 1;

    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    private SeatReservationService service;

    private final SeatRepository seatRepo
            = mock(SeatRepository.class);
    
    private final SeatReservationRepository reservationRepo
            = mock(SeatReservationRepository.class);

    private SeatReservationRequest request;
    private User user;
    private Seat seat;
    private SeatReservation seatReservation;

    @BeforeEach
    void setUp() {
        service = new SeatReservationService(
                seatRepo,
                reservationRepo
        );

        user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        seat = Seat.builder()
                .id(SEAT_ID)
                .number(SEAT_NUMBER)
                .status(false)
                .build();

        seatReservation = SeatReservation.builder()
                .id(SEAT_RESERVATION_ID)
                .date(DATE)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .canceled(false)
                .user(user)
                .seat(seat)
                .build();
    }

    @Nested
    @DisplayName("addReservation 메서드는")
    class Describe_addReservation_method {
        @BeforeEach
        void setUp() {
            request = SeatReservationRequest.builder()
                    .checkIn(CHECK_IN)
                    .checkOut(CHECK_OUT)
                    .build();

            given(seatRepo.findByNumber(eq(SEAT_NUMBER)))
                    .willReturn(Optional.of(seat));

            given(reservationRepo.save(any(SeatReservation.class)))
                    .will(invocation -> {
                        SeatReservation seatReservation = invocation.getArgument(0);

                        return SeatReservation.builder()
                                .id(SEAT_RESERVATION_ID)
                                .date(seatReservation.getDate())
                                .checkIn(seatReservation.getCheckIn())
                                .checkOut(seatReservation.getCheckOut())
                                .canceled(false)
                                .user(user)
                                .seat(seat)
                                .build();
                    });
        }

        @Nested
        @DisplayName("예약 정보를 반환한다")
        class It_returns_seat_reservation_data {
            SeatReservation subject() {
                return service.addReservation(
                        SEAT_NUMBER, 
                        request, 
                        user
                );
            }

            @Test
            void test() {
                SeatReservation seatReservation = subject();

                assertThat(seatReservation.getId()).isEqualTo(SEAT_RESERVATION_ID);
                assertThat(seatReservation.getDate()).isEqualTo(DATE);
                assertThat(seatReservation.getCheckIn()).isEqualTo(CHECK_IN);
                assertThat(seatReservation.getCheckOut()).isEqualTo(CHECK_OUT);
                assertThat(seatReservation.getCanceled()).isEqualTo(false);
            }
        }

        @Nested
        @DisplayName("만약 오늘 다른 좌석을 이미 예약했다면")
        class Context_if_already_reserved_another_seat_today {
            @BeforeEach
            void setUp() {
                user = User.builder()
                        .id(USER_ID)
                        .name(NAME)
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build();
            }

            SeatReservation subject() {
                return service.addReservation(SEAT_NUMBER, request, user);
            }

            @Nested
            @DisplayName("UserAlreadyReservedSeatTodayException 예외를 던진다")
            class It_throws_user_already_reserved_seat_today_exception {
                @Test
                void test() {
                    assertThatThrownBy(() -> subject())
                            .isInstanceOf(UserAlreadyReservedSeatTodayException.class);
                }
            }
        }
    }
}
