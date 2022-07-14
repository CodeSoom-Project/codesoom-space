package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatRepository;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.SeatReservationRepository;
import com.codesoom.myseat.dto.SeatReservationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SeatReservationServiceTest {
    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 3;
    private static final String USER_NAME = "코드숨";
    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    private SeatReservationService seatReservationService;

    private final SeatRepository seatRepository
            = mock(SeatRepository.class);
    private final SeatReservationRepository seatReservationRepository
            = mock(SeatReservationRepository.class);

    private SeatReservationRequest seatReservationRequest;

    @BeforeEach
    void setUp() {
        seatReservationService
                = new SeatReservationService(seatRepository, seatReservationRepository);
    }

    @Nested
    @DisplayName("addReservation 메서드는")
    class Describe_addReservation_method {
        @BeforeEach
        void setUp() {
            seatReservationRequest = SeatReservationRequest.builder()
                    .userName(USER_NAME)
                    .checkIn(CHECK_IN)
                    .checkOut(CHECK_OUT)
                    .build();

            given(seatRepository.findByNumber(eq(SEAT_NUMBER)))
                    .willReturn(Optional.of(Seat.builder()
                            .id(SEAT_ID)
                            .number(SEAT_NUMBER)
                            .userName("")
                            .build()));

            given(seatReservationRepository.save(any(SeatReservation.class)))
                    .will(invocation -> {
                        SeatReservation seatReservation = invocation.getArgument(0);

                        return SeatReservation.builder()
                                .id(SEAT_RESERVATION_ID)
                                .seatNumber(seatReservation.getSeatNumber())
                                .userName(seatReservation.getUserName())
                                .date(seatReservation.getDate())
                                .checkIn(seatReservation.getCheckIn())
                                .checkOut(seatReservation.getCheckOut())
                                .build();
                    });
        }

        @Nested
        @DisplayName("예약 정보를 반환한다")
        class It_returns_seat_reservation_data {
            SeatReservation subject() {
                return seatReservationService.addReservation(SEAT_NUMBER, seatReservationRequest);
            }

            @Test
            void test() {
                SeatReservation seatReservation = subject();
                assertThat(seatReservation.getId()).isEqualTo(SEAT_RESERVATION_ID);
                assertThat(seatReservation.getSeatNumber()).isEqualTo(SEAT_NUMBER);
                assertThat(seatReservation.getUserName()).isEqualTo(USER_NAME);
                assertThat(seatReservation.getDate()).isEqualTo(DATE);
                assertThat(seatReservation.getCheckIn()).isEqualTo(CHECK_IN);
                assertThat(seatReservation.getCheckOut()).isEqualTo(CHECK_OUT);
            }
        }
    }
}
