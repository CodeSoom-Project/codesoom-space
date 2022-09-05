//package com.codesoom.myseat.controllers;
//
//import com.codesoom.myseat.domain.SeatReservation;
//import com.codesoom.myseat.dto.SeatReservationCancelRequest;
//import com.codesoom.myseat.services.SeatReservationCancelService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(SeatReservationCancelController.class)
//@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.codesoom-myseat.site")
//class SeatReservationCancelControllerTest {
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
//    private SeatReservationCancelService cancelService;
//
//    private SeatReservationCancelRequest request;
//    private SeatReservation reservation;
//
//    @Test
//    @DisplayName("좌석 예약 취소 테스트")
//    void test() throws Exception {
//        // given
//        request = SeatReservationCancelRequest.builder()
//                .userName(USER_NAME)
//                .build();
//
//        reservation = SeatReservation.builder()
//                .id(SEAT_RESERVATION_ID)
//                .seatNumber(SEAT_NUMBER)
//                .userName(USER_NAME)
//                .date(DATE)
//                .checkIn(CHECK_IN)
//                .checkOut(CHECK_OUT)
//                .build();
//
//        given(cancelService.cancelReservation(eq(SEAT_NUMBER), any(SeatReservationCancelRequest.class)))
//                .willReturn(reservation);
//
//        // when
//        ResultActions subject = mockMvc.perform(delete("/seat-reservation/{seatNumber}", SEAT_NUMBER)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson(request)));;
//
//        // then
//        subject.andExpect(status().isNoContent());
//
//        // docs
//        subject.andDo(document("seat-reservation-cancel",
//                preprocessRequest(prettyPrint()),
//                preprocessResponse(prettyPrint()),
//                pathParameters(
//                        parameterWithName("seatNumber").description("좌석 번호")
//                ),
//                requestFields(
//                        fieldWithPath("userName").type(JsonFieldType.STRING).description("회원 이름")
//                )
//        ));
//    }
//
//    private String toJson(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(object);
//    }
//}
