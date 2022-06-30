package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.services.SeatReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatReservationController.class)
class SeatReservationControllerTest {
    private static final Long SEAT_ID = 3L;
    private static final Long USER_ID = 1L;
    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE = "2022-06-30";
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatReservationService seatReservationService;

    private SeatReservationRequest seatReservationRequest;
    private SeatReservation seatReservation;

    @Nested
    @DisplayName("좌석 예약 요청 시")
    class Describe_seat_reservation_request {
        @BeforeEach
        void setUp() {
            seatReservationRequest = SeatReservationRequest.builder()
                    .userId(USER_ID)
                    .checkIn(CHECK_IN)
                    .checkOut(CHECK_OUT)
                    .build();

            seatReservation = SeatReservation.builder()
                    .id(SEAT_RESERVATION_ID)
                    .seatId(SEAT_ID)
                    .userId(USER_ID)
                    .date(DATE)
                    .checkIn(CHECK_IN)
                    .checkOut(CHECK_OUT)
                    .build();

            given(seatReservationService.addReservation(eq(SEAT_ID), any(SeatReservationRequest.class)))
                    .willReturn(seatReservation);
        }

        @Nested
        @DisplayName("상태 코드 201과 좌석 예약 정보를 응답한다")
        class It_responses_status_code_201_and_seat_reservation_data {
            ResultActions subject() throws Exception {
                return mockMvc.perform(post("/seat/{id}", SEAT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(seatReservationRequest)));
            }

            @Test
            void test() throws Exception {
                subject().andExpect(status().isCreated())
                        .andExpect(jsonPath("$.userId").value(USER_ID))
                        .andExpect(jsonPath("$.seatId").value(SEAT_ID))
                        .andExpect(jsonPath("$.date").value(DATE))
                        .andExpect(jsonPath("$.checkIn").value(CHECK_IN))
                        .andExpect(jsonPath("$.checkOut").value(CHECK_OUT));
            }
        }
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
