package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.services.AuthenticationService;
import com.codesoom.myseat.services.SeatReservationService;
import com.codesoom.myseat.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
@AutoConfigureRestDocs(
        uriScheme = "https", 
        uriHost = "api.codesoom-myseat.site"
)
class SeatReservationControllerTest {
    private static final String VALID_TOKEN 
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";

    private static final Long SEAT_ID = 1L;
    private static final int SEAT_NUMBER = 1;

    private static final Long SEAT_RESERVATION_ID = 1L;
    private static final String DATE
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static final String CHECK_IN = "09:30";
    private static final String CHECK_OUT = "17:30";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private SeatReservationService seatReservationService;

    @MockBean
    private UserService userService;

    private SeatReservationRequest request;
    private SeatReservation seatReservation;
    private User user;
    private Seat seat;

    @BeforeEach
    void setUp() {
        request = SeatReservationRequest.builder()
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .build();

        user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        seat = Seat.builder()
                .id(SEAT_ID)
                .number(SEAT_NUMBER)
                .status(false)
                .build();

        seatReservation = SeatReservation.builder()
                .id(SEAT_RESERVATION_ID)
                .date(DATE)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .user(user)
                .seat(seat)
                .build();

        given(userService.findUser(EMAIL))
                .willReturn(user);

        given(seatReservationService.addReservation(
                eq(SEAT_NUMBER), 
                any(SeatReservationRequest.class), 
                any(User.class)
        )).willReturn(seatReservation);

        given(authService.parseToken(VALID_TOKEN))
                .willReturn(EMAIL);
    }

    @Test
    @DisplayName("좌석 예약 요청 테스트")
    void test() throws Exception {
        // when
        ResultActions subject 
                = mockMvc.perform(
                        post("/seat-reservation/{number}", SEAT_NUMBER)
                                .header("Authorization", "Bearer " + VALID_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(request)));

        // then
        subject.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.number").value(SEAT_NUMBER))
                .andExpect(jsonPath("$.date").value(DATE))
                .andExpect(jsonPath("$.checkIn").value(CHECK_IN))
                .andExpect(jsonPath("$.checkOut").value(CHECK_OUT));

        // docs
        subject.andDo(document("seat-reservation",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                        parameterWithName("number").description("좌석 번호")
                ),
                requestFields(
                        fieldWithPath("checkIn").type(JsonFieldType.STRING).description("체크인"),
                        fieldWithPath("checkOut").type(JsonFieldType.STRING).description("체크아웃")
                ),
                responseFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                        fieldWithPath("number").type(JsonFieldType.NUMBER).description("좌석 번호"),
                        fieldWithPath("date").type(JsonFieldType.STRING).description("예약 날짜"),
                        fieldWithPath("checkIn").type(JsonFieldType.STRING).description("체크인"),
                        fieldWithPath("checkOut").type(JsonFieldType.STRING).description("체크아웃")
                )
        ));
    }

    private String toJson(
            Object object
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
