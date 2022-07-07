package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.services.SeatReservationListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatReservationListController.class)
class SeatReservationListControllerTest {
    private static final int SEAT_NUMBER = 3;
    private static final String USER_NAME = "코드숨";
    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE = "2022-06-30";
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatReservationListService service;

    private SeatReservation reservation;

    @Nested
    @DisplayName("좌석 예약 목록 조회 요청 시")
    class Describe_seat_reservation_list_request {
        @BeforeEach
        void setUp() {
            reservation = SeatReservation.builder()
                    .id(SEAT_RESERVATION_ID)
                    .seatNumber(SEAT_NUMBER)
                    .userName(USER_NAME)
                    .date(DATE)
                    .checkIn(CHECK_IN)
                    .checkOut(CHECK_OUT)
                    .build();

            given(service.seatReservations()).willReturn(List.of(reservation));
        }

        @Nested
        @DisplayName("상태 코드 200과 좌석 예약 목록 정보를 응답한다")
        class It_responses_status_code_201_and_seat_reservation_list_data {
            ResultActions subject() throws Exception {
                return mockMvc.perform(get("/seat-reservations"));
            }

            @Test
            void test() throws Exception {
                subject().andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].seatNumber").value(SEAT_NUMBER))
                        .andExpect(jsonPath("$[0].userName").value(USER_NAME));
            }
        }
    }
}
