package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.SeatReservationCancelRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatReservationCancelController.class)
@AutoConfigureRestDocs
class SeatReservationCancelControllerTest {
    private static final int SEAT_NUMBER = 3;
    private static final String USER_NAME = "코드숨";

    @Autowired
    private MockMvc mockMvc;

    private SeatReservationCancelRequest request;

    @Test
    @DisplayName("좌석 예약 취소 테스트")
    void test() throws Exception {
        // given
        request = SeatReservationCancelRequest.builder()
                .userName(USER_NAME)
                .build();

        // when
        ResultActions subject = mockMvc.perform(delete("/seat-reservation/{seatNumber}", SEAT_NUMBER));

        // then
        subject.andExpect(status().isNoContent());
    }
}
