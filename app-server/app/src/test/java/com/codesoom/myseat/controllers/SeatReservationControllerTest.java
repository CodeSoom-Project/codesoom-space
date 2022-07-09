package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.services.SeatReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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
    private static final int SEAT_NUMBER = 3;
    private static final String USER_NAME = "코드숨";
    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE = "2022-06-30";
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatReservationService service;

    private SeatReservationRequest request;
    private SeatReservation reservation;

    @Test
    @DisplayName("좌석 예약 요청 테스트")
    void test() throws Exception {
        // given
        request = SeatReservationRequest.builder()
                .userName(USER_NAME)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .build();

        reservation = SeatReservation.builder()
                .id(SEAT_RESERVATION_ID)
                .seatNumber(SEAT_NUMBER)
                .userName(USER_NAME)
                .date(DATE)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .build();

        given(service.addReservation(eq(SEAT_NUMBER), any(SeatReservationRequest.class)))
                .willReturn(reservation);

        // when
        ResultActions subject = mockMvc.perform(post("/seat/{number}", SEAT_NUMBER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        subject.andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value(USER_NAME))
                .andExpect(jsonPath("$.seatNumber").value(SEAT_NUMBER))
                .andExpect(jsonPath("$.date").value(DATE))
                .andExpect(jsonPath("$.checkIn").value(CHECK_IN))
                .andExpect(jsonPath("$.checkOut").value(CHECK_OUT));
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
