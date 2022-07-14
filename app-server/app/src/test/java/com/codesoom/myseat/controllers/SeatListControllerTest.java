package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.services.SeatListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
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
@AutoConfigureRestDocs
class SeatListControllerTest {
    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 1;
    private static final String USER_NAME = "코드숨";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatListService service;

    private Seat seat;

    @Test
    @DisplayName("좌석 예약 목록 조회 요청 테스트")
    void test() throws Exception {
        // given
        seat = Seat.builder()
                .id(SEAT_ID)
                .number(SEAT_NUMBER)
                .userName(USER_NAME)
                .build();

        given(service.seats()).willReturn(List.of(seat));

        // when
        ResultActions subject = mockMvc.perform(get("/seats"));

        // then
        subject.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].seatNumber").value(SEAT_NUMBER))
                .andExpect(jsonPath("$[0].userName").value(USER_NAME));
    }
}
