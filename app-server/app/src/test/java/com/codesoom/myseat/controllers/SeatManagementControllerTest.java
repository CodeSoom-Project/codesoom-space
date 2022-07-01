package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.dto.SeatAddRequest;
import com.codesoom.myseat.services.SeatManagementService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatManagementController.class)
class SeatManagementControllerTest {
    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 3;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatManagementService seatManagementService;

    private SeatAddRequest seatAddRequest;
    private Seat seat;

    @Nested
    @DisplayName("좌석 추가 요청 시")
    class Describe_seat_add_request {
        @BeforeEach
        void setUp() {
            seatAddRequest = SeatAddRequest.builder()
                    .number(SEAT_NUMBER)
                    .build();

            seat = Seat.builder()
                    .id(SEAT_ID)
                    .number(SEAT_NUMBER)
                    .build();

            given(seatManagementService.addSeat(any(SeatAddRequest.class)))
                    .willReturn(seat);
        }

        @Nested
        @DisplayName("상태 코드 201과 좌석 정보를 응답한다")
        class It_responses_status_code_201_and_seat_data {
            ResultActions subject() throws Exception {
                return mockMvc.perform(post("/seat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(seatAddRequest)));
            }

            @Test
            void test() throws Exception {
                subject().andExpect(status().isCreated())
                        .andExpect(jsonPath("$.number").value(SEAT_NUMBER));
            }
        }
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
