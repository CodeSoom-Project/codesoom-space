package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.services.SeatListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatListController.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "15.164.164.136", uriPort = 8080)
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
    @DisplayName("좌석 목록 조회 요청 테스트")
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

        // docs
        subject.andDo(document("seat-list",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].seatNumber").type(JsonFieldType.NUMBER).description("좌석 번호"),
                        fieldWithPath("[].userName").type(JsonFieldType.STRING).description("회원 이름")
                )
        ));
    }
}
