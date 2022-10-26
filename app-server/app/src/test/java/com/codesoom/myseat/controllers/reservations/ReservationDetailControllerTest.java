package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.InvalidTokenException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationDetailService;
import com.codesoom.myseat.services.users.UserService;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReservationDetailController.class)
class ReservationDetailControllerTest {

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private UserService userService;

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
                .defaultRequest(get("/reservations/*")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + ACCESS_TOKEN))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @DisplayName("주어진 예약 id로 예약 정보를 찾을 수 있으면 예약 정보를 응답한다.")
    @Test
    void GET_reservation_with_200_status() throws Exception {
        //given
        Long reservationId = 1L;
        String content = "코테풀기, 공부, 과제";
        given(reservationDetailService.reservation(anyLong(), anyLong()))
                .willReturn(Reservation.builder()
                        .id(reservationId)
                        .plan(Plan.builder().id(1L).content(content).build())
                        .date(new Date("2022-10-13"))
                        .build());

        //when
        ResultActions perform
                = mockMvc.perform(get(
                        String.format("/reservations/%d", reservationId)));

        //then
        MvcResult result = perform.andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).contains(content);
    }

    @DisplayName("주어진 예약 id로 예약 정보를 찾을 수 없으면 404 not found를 응답한다.")
    @Test
    void GET_reservation_with_404_status() throws Exception {
        //given
        Long notExistReservationId = 100L;
        given(reservationDetailService.reservation(
                eq(notExistReservationId), anyLong()))
                .willThrow(ReservationNotFoundException.class);

        //when & then
        mockMvc.perform(get(
                String.format("/reservations/%d", notExistReservationId)))
                .andExpect(status().isNotFound());
    }

    @DisplayName("유효하지 않은 토큰이 주어지면 401 unauthorized를 응답한다.")
    @Test
    void GET_reservation_with_401_status() throws Exception {
        //given
        Long reservationId = 1L;
        given(authService.parseToken(anyString()))
                .willThrow(new InvalidTokenException());

        //when & then
        mockMvc.perform(get(String.format("/reservations/%d", reservationId))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + "akdjfisdlkfajsdlk"))
                .andExpect(status().isUnauthorized());
    }
    
}
