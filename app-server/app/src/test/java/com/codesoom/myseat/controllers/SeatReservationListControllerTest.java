//package com.codesoom.myseat.controllers;
//
//import com.codesoom.myseat.domain.SeatReservation;
//import com.codesoom.myseat.services.SeatReservationListService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(SeatReservationListController.class)
//@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.codesoom-myseat.site")
//class SeatReservationListControllerTest {
//    private static final int SEAT_NUMBER = 3;
//    private static final String USER_NAME = "코드숨";
//    private static final Long SEAT_RESERVATION_ID = 1L;
//    private static final String DATE = "2022-06-30";
//    private static final String CHECK_IN = "09:30";
//    private static final String CHECK_OUT = "17:30";
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SeatReservationListService service;
//
//    private SeatReservation reservation;
//
//    @Test
//    @DisplayName("좌석 예약 목록 조회 요청 테스트")
//    void test() throws Exception {
//        // given
//        reservation = SeatReservation.builder()
//                .id(SEAT_RESERVATION_ID)
//                .seatNumber(SEAT_NUMBER)
//                .userName(USER_NAME)
//                .date(DATE)
//                .checkIn(CHECK_IN)
//                .checkOut(CHECK_OUT)
//                .build();
//
//        given(service.seatReservations()).willReturn(List.of(reservation));
//
//        // when
//        ResultActions subject = mockMvc.perform(get("/seat-reservations"));
//
//        // then
//        subject.andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].seatNumber").value(SEAT_NUMBER))
//                .andExpect(jsonPath("$[0].userName").value(USER_NAME));
//
//        // docs
//        subject.andDo(document("seat-reservations",
//                preprocessRequest(prettyPrint()),
//                preprocessResponse(prettyPrint()),
//                responseFields(
//                        fieldWithPath("[].userName").type(JsonFieldType.STRING).description("회원 이름"),
//                        fieldWithPath("[].seatNumber").type(JsonFieldType.NUMBER).description("좌석 번호"),
//                        fieldWithPath("[].date").type(JsonFieldType.STRING).description("예약 날짜"),
//                        fieldWithPath("[].checkIn").type(JsonFieldType.STRING).description("체크인 시간"),
//                        fieldWithPath("[].checkOut").type(JsonFieldType.STRING).description("체크아웃 시간")
//                )
//        ));
//    }
//}
