package com.codesoom.myseat.controllers.reservations.retrospectives;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationDetailService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveDetailService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveAddService;
import com.codesoom.myseat.services.users.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RetrospectiveDetailController.class)
class RetrospectiveDetailControllerTest {

    private static final Role VERIFIED_USER_ROLE
            = new Role(1L, 1L, "VERIFIED_USER");

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private RetrospectiveAddService retrospectiveAddService;

    @MockBean
    private UserService userService;

    @MockBean
    private ReservationDetailService reservationDetailService;

    @MockBean
    private RetrospectiveDetailService retrospectiveDetailService;

    @Test
    @DisplayName("POST /reservations/{id}/retrospectives 요청 시 상태코드 200과 함께 회고를 반환한다")
    void get_retrospective_returns_retrospective_and_responses_status_code_200() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        Plan mockPlan = Plan.builder()
                .id(2L)
                .content("코테 풀기")
                .build();

        Reservation mockReservation = Reservation.builder()
                .id(3L)
                .date(new Date("2022-10-18"))
                .user(mockUser)
                .plan(mockPlan)
                .build();

        Retrospective mockRetrospective = Retrospective.builder()
                .id(4L)
                .content("잘했다.")
                .reservation(mockReservation)
                .build();

        given(authService.roles(any()))
                .willReturn(List.of(VERIFIED_USER_ROLE));

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn(1L);

        given(userService.findById(1L))
                .willReturn(mockUser);

        given(reservationDetailService.reservationOfUser(3L, 1L))
                .willReturn(mockReservation);

        given(retrospectiveDetailService.retrospective(3L))
                .willReturn(mockRetrospective);

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn(1L);

        ResultActions result = mockMvc.perform(get("/reservations/{id}/retrospectives", 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.content").value("잘했다."))
                .andDo(print())
                .andReturn();
    }
}
