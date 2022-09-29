package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.services.AuthenticationService;
import com.codesoom.myseat.services.SeatReservationCancelService;
import com.codesoom.myseat.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatReservationCancelController.class)
@AutoConfigureRestDocs(
        uriScheme = "https", 
        uriHost = "api.codesoom-myseat.site"
)
class SeatReservationCancelControllerTest {
    private static final String VALID_TOKEN 
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private static final Long USER_ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";

    private static final int SEAT_NUMBER = 3;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private SeatReservationCancelService cancelService;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        given(userService.findUser(EMAIL))
                .willReturn(user);

        given(authService.parseToken(VALID_TOKEN))
                .willReturn(EMAIL);
    }

    @Test
    @DisplayName("좌석 예약 취소 테스트")
    void test() throws Exception {
        // when
        ResultActions subject 
                = mockMvc.perform(
                        delete("/seat-reservation/{seatNumber}", SEAT_NUMBER)
                                .header("Authorization", "Bearer " + VALID_TOKEN));

        // then
        subject.andExpect(status().isOk());

        // docs
        subject.andDo(document("seat-reservation-cancel",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                        parameterWithName("seatNumber").description("좌석 번호")
                )
        ));
    }
}
