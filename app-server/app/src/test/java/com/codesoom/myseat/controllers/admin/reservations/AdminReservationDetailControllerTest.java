package com.codesoom.myseat.controllers.admin.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminReservationDetailController.class)
class AdminReservationDetailControllerTest {

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final Role USER_ROLE = new Role(1L, 1L, "USER");
    private static final Role ADMIN_ROLE = new Role(1L, 1L, "ADMIN");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private ReservationDetailService reservationDetailService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(
                        new CharacterEncodingFilter(
                                "UTF-8", true))
                .defaultRequest(get("/admin/reservations/*")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + ACCESS_TOKEN))
                .apply(springSecurity())
                .build();
    }

    @DisplayName("GET /admin/reservation/{id}는 예약 정보를 응답합니다.")
    @Test
    void GET_reservation_responses_reservation() throws Exception {
        Long reservationId = 1L;
        String content = "코테풀기, 공부, 과제";

        given(authService.roles(any())).willReturn(List.of(ADMIN_ROLE));

        given(reservationDetailService.reservation(reservationId))
                .willReturn(Reservation.builder()
                        .id(reservationId)
                        .plan(Plan.builder().id(1L).content(content).build())
                        .date(new Date("2022-10-13"))
                        .build());

        ResultActions perform
                = mockMvc.perform(
                        get("/admin/reservations/" + reservationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content));
    }

    @DisplayName("GET /admin/reservation/{id}는 존재하지 않은 예약을 조회하면 " +
            "404를 응답합니다")
    @Test
    void GET_reservation_with_not_exists_responses_404() throws Exception {
        Long reservationId = 1L;

        given(authService.roles(any())).willReturn(List.of(ADMIN_ROLE));

        given(reservationDetailService.reservation(reservationId))
                .willThrow(ReservationNotFoundException.class);

        ResultActions perform
                = mockMvc.perform(
                        get("/admin/reservations/" + reservationId))
                .andExpect(status().isNotFound());
    }

    @DisplayName("GET /admin/reservation/{id}는 관리자 권한이 없는 사용자가 " +
            "요청하면 403을 응답합니다")
    @Test
    void GET_reservation_with_not_admin_responses_403() throws Exception {
        given(authService.roles(any())).willReturn(List.of(USER_ROLE));

        ResultActions perform
                = mockMvc.perform(get("/admin/reservations/1"))
                .andExpect(status().isForbidden());
    }

}
