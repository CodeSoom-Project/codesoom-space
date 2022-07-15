package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.services.SeatReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatReservationController.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "15.164.164.136", uriPort = 8080)
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
        ResultActions subject = mockMvc.perform(post("/seat-reservation/{seatNumber}", SEAT_NUMBER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        subject.andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value(USER_NAME))
                .andExpect(jsonPath("$.seatNumber").value(SEAT_NUMBER))
                .andExpect(jsonPath("$.date").value(DATE))
                .andExpect(jsonPath("$.checkIn").value(CHECK_IN))
                .andExpect(jsonPath("$.checkOut").value(CHECK_OUT));

        // docs
        subject.andDo(document("seat-reservation",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                        parameterWithName("seatNumber").description("좌석 번호")
                ),
                requestFields(
                        fieldWithPath("userName").type(JsonFieldType.STRING).description("회원 이름"),
                        fieldWithPath("checkIn").type(JsonFieldType.STRING).description("체크인 시간"),
                        fieldWithPath("checkOut").type(JsonFieldType.STRING).description("체크아웃 시간")
                ),
                responseFields(
                        fieldWithPath("userName").type(JsonFieldType.STRING).description("회원 이름"),
                        fieldWithPath("seatNumber").type(JsonFieldType.NUMBER).description("좌석 번호"),
                        fieldWithPath("date").type(JsonFieldType.STRING).description("예약 날짜"),
                        fieldWithPath("checkIn").type(JsonFieldType.STRING).description("체크인 시간"),
                        fieldWithPath("checkOut").type(JsonFieldType.STRING).description("체크아웃 시간")
                )
        ));
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
