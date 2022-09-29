package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SeatDetailResponse;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.AuthenticationService;
import com.codesoom.myseat.services.SeatDetailService;
import com.codesoom.myseat.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatDetailController.class)
@AutoConfigureRestDocs(
        uriScheme = "https", 
        uriHost = "api.codesoom-myseat.site"
)
class SeatDetailControllerTest {
    private static final String VALID_TOKEN 
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";

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
    private UserAuthentication userAuth;

    @MockBean
    private SeatDetailService seatDetailService;

    @MockBean
    private UserService userService;

    private User user;
    private SeatReservation reservation;
    private SeatDetailResponse response;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        reservation = SeatReservation.builder()
                .id(SEAT_RESERVATION_ID)
                .date(DATE)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .build();

        response = SeatDetailResponse.builder()
                .seatNumber(SEAT_NUMBER)
                .date(DATE)
                .checkIn(CHECK_IN)
                .checkOut(CHECK_OUT)
                .userName(NAME)
                .build();

        given(userService.findUser(EMAIL))
                .willReturn(user);

        given(seatDetailService.seatDetail(any(int.class), any(User.class)))
                .willReturn(response);

        given(authService.parseToken(VALID_TOKEN))
                .willReturn(EMAIL);
    }

    @Test
    @DisplayName("좌석 상세 조회 요청 테스트")
    void test() throws Exception {
        // when
        ResultActions subject 
                = mockMvc.perform(
                        get("/seat/{seatNumber}", SEAT_NUMBER)
                                .header("Authorization", "Bearer " + VALID_TOKEN));

        // then
        subject.andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber").value(SEAT_NUMBER))
                .andExpect(jsonPath("$.date").value(DATE))
                .andExpect(jsonPath("$.checkIn").value(CHECK_IN))
                .andExpect(jsonPath("$.checkOut").value(CHECK_OUT))
                .andExpect(jsonPath("$.userName").value(NAME));

        // docs
        subject.andDo(document("seat-detail",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                        parameterWithName("seatNumber").description("좌석 번호")
                ),
                responseFields(
                        fieldWithPath("userName").type(JsonFieldType.STRING).description("예약자명"),
                        fieldWithPath("seatNumber").type(JsonFieldType.NUMBER).description("좌석 번호"),
                        fieldWithPath("date").type(JsonFieldType.STRING).description("예약 날짜"),
                        fieldWithPath("checkIn").type(JsonFieldType.STRING).description("체크인"),
                        fieldWithPath("checkOut").type(JsonFieldType.STRING).description("체크아웃")
                )
        ));
    }
}
