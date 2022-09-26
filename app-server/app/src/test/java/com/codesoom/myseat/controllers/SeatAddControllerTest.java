//package com.codesoom.myseat.controllers;
//
//import com.codesoom.myseat.domain.Seat;
//import com.codesoom.myseat.dto.SeatAddRequest;
//import com.codesoom.myseat.services.SeatAddService;
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
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(SeatAddController.class)
//@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.codesoom-myseat.site")
//class SeatAddControllerTest {
//    private static final Long SEAT_ID = 1L;
//    private static final int SEAT_NUMBER = 3;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SeatAddService service;
//
//    private SeatAddRequest request;
//    private Seat seat;
//
//    @Test
//    @DisplayName("좌석 추가 요청 테스트")
//    void test() throws Exception {
//        // given
//        request = SeatAddRequest.builder()
//                .number(SEAT_NUMBER)
//                .build();
//
//        seat = Seat.builder()
//                .id(SEAT_ID)
//                .number(SEAT_NUMBER)
//                .build();
//
//        given(service.addSeat(any(SeatAddRequest.class))).willReturn(seat);
//
//        // when
//        ResultActions subject = mockMvc.perform(post("/seat")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson(request)));
//
//        // then
//        subject.andExpect(status().isCreated())
//                .andExpect(jsonPath("$.number").value(SEAT_NUMBER));
//
//        // docs
//        subject.andDo(document("seat-add",
//                preprocessRequest(prettyPrint()),
//                preprocessResponse(prettyPrint()),
//                requestFields(
//                        fieldWithPath("number").type(JsonFieldType.NUMBER).description("좌석 번호")
//                ),
//                responseFields(
//                        fieldWithPath("number").type(JsonFieldType.NUMBER).description("좌석 번호")
//                )
//        ));
//    }
//
//    private String toJson(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(object);
//    }
//}
