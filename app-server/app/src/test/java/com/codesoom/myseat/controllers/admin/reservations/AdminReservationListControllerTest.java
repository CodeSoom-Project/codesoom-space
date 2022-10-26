package com.codesoom.myseat.controllers.admin.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationListService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminReservationListController.class)
class AdminReservationListControllerTest {
    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    Role USER_ROLE = new Role(1L, 1L, "USER");
    Role ADMIN_ROLE = new Role(1L, 1L, "ADMIN");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private ReservationListService reservationListService;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .build();
    }

    @DisplayName("GET /admin/reservations을 admin인 사람이 요청하면 " +
            "예약 목록을 응답합니다.")
    @Test
    void GET_admin_reservations_responses_all_reservations() throws Exception {
        given(authService.roles(any())).willReturn(List.of(ADMIN_ROLE));

        String content = "코테풀기, 공부, 과제";
        given(reservationListService.allReservations())
                .willReturn(List.of(
                        Reservation.builder()
                                .id(1L)
                                .plan(Plan.builder().id(1L).content(content).build())
                                .date(new Date("2022-10-13"))
                                .build()
                ));


        mockMvc.perform(get("/admin/reservations")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservations[0].content")
                        .value(content));
    }

    @DisplayName("GET /admin/reservations을 admin이 아닌 사람이 요청하면 " +
            "403을 응답합니다.")
    @Test
    void GET_admin_reservations_responses_403() throws Exception {
        given(authService.roles(any())).willReturn(List.of(USER_ROLE));

        mockMvc.perform(get("/admin/reservations")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
        ).andExpect(status().isForbidden());
    }
}
