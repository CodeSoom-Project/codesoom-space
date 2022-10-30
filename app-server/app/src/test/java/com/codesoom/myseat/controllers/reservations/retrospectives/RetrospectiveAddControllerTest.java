package com.codesoom.myseat.controllers.reservations.retrospectives;

import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationAddService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveAddService;
import com.codesoom.myseat.services.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(RetrospectiveAddController.class)
class RetrospectiveAddControllerTest {

    private static final Role VERIFIED_USER_ROLE
            = new Role(1L, 1L, "VERIFIED_USER");

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private static final String INVALID_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk2";

    private static final Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private RetrospectiveAddService retrospectiveAddService;

    @MockBean
    private ReservationAddService reservationAddService;

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter(
                        "UTF-8", true))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();

        given(authService.roles(any()))
                .willReturn(List.of(VERIFIED_USER_ROLE));
    }

    @Test
    @DisplayName("POST /retrospectives 요청 시 상태코드 204를 응답한다")
    void retrospective_valid_Token() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        Plan mockPlan = Plan.builder()
                .id(2L)
                .content("밥먹기, 코테풀기")
                .build();

        Reservation mockReservation = Reservation.builder()
                .id(3L)
                .user(mockUser)
                .plan(mockPlan)
                .build();

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn(1L);

        given(userService.findById(1L))
                .willReturn(mockUser);

        given(reservationAddService.findReservation(1L))
                .willReturn(mockReservation);

        RetrospectiveRequest request = RetrospectiveRequest.builder()
                .content("상호작용의 첫 단계는 사용자로부터 시작합니다. \n" +
                        "직관적인 입력을 제공하여 사용하기 쉬운 편안한 환경을 사용자에게 제공해 줄 수 있습니다. \n" +
                        "우리가 만드는 코드숨 공부방 좌석 예약 api에서 좌석을 예약하는 기능을 예로 들어보겠습니다. ")
                .build();

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn(ID);

        ResultActions result = mockMvc.perform(post("/reservations/{id}/retrospectives", ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .content(toJson(request))
        );

        result.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print())
                .andReturn();
    }

    private String toJson(
            Object object
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
