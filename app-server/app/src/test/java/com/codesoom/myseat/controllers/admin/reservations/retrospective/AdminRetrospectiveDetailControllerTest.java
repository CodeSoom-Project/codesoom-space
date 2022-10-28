package com.codesoom.myseat.controllers.admin.reservations.retrospective;

import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveDetailService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminRetrospectiveDetailController.class)
class AdminRetrospectiveDetailControllerTest {

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final Role USER_ROLE = new Role(1L, 1L, "USER");
    private static final Role ADMIN_ROLE = new Role(1L, 1L, "ADMIN");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private RetrospectiveDetailService retrospectiveDetailService;

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

    @DisplayName("GET /admin/reservation/{id}/retrospectives " +
            "회고 정보를 응답합니다.")
    @Test
    void GET_reservation_responses_retrospective() throws Exception {
        Long reservationId = 1L;
        LocalDateTime NOW = LocalDateTime.of(2022, 10, 26, 17, 22, 0);

        given(authService.roles(any())).willReturn(List.of(ADMIN_ROLE));

        given(retrospectiveDetailService.retrospective(reservationId))
                .willReturn(Retrospective.builder()
                        .id(reservationId)
                        .content("잘했다.")
                        .createdDate(NOW)
                        .build());

        ResultActions perform
                = mockMvc.perform(
                        get("/admin/reservations/"
                                + reservationId + "/retrospectives"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                "{\"id\":1,\"content\":\"잘했다.\",\"createdDate\":\"2022-10-26T17:22:00\"}"))
                );

    }

    @DisplayName("GET /admin/reservation/{id}/retrospectives는 " +
            "관리자 권한 없은 사용자가 요청하면 403 에러를 던집니다.")
    @Test
    void GET_retrospectives_with_not_admin_responses_403() throws Exception {
        given(authService.roles(any())).willReturn(List.of(USER_ROLE));

        ResultActions perform
                = mockMvc.perform(get("/admin/reservations/1" +
                        "/retrospectives"))
                .andExpect(status().isForbidden());
    }

}
