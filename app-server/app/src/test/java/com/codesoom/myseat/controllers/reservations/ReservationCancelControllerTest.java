package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationCancelService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReservationCancelController.class)
class ReservationCancelControllerTest {

    private static final Role VERIFIED_USER_ROLE
            = new Role(1L, 1L, "VERIFIED_USER");

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private ReservationCancelService reservationCancelService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter(
                        "UTF-8", true))
                .defaultRequest(
                        patch("/reservations/*")
                                .header(HttpHeaders.AUTHORIZATION,
                                        "Bearer " + ACCESS_TOKEN))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();

        given(authService.roles(any()))
                .willReturn(List.of(VERIFIED_USER_ROLE));
    }

    @DisplayName("예약을 성공적으로 취소하면 204 no content를 응답한다.")
    @Test
    void PUT_reservation_cancel_with_204_status() throws Exception {
        mockMvc.perform(patch("/reservations/1"))
                .andExpect(status().isNoContent());
    }

    @DisplayName("본인 소유가 아닌 예약 id가 주어지면 403 forbidden를 응답한다.")
    @Test
    void PUT_reservation_update_with_403_status() throws Exception {
        doThrow(NotOwnedReservationException.class)
                .when(reservationCancelService)
                .cancelReservation(anyLong(), anyLong());

        mockMvc.perform(patch("/reservations/999"))
                .andExpect(status().isForbidden());
    }

    @DisplayName("찾을 수 없는 예약 id가 주어지면 404 not found를 응답한다.")
    @Test
    void PUT_reservation_update_with_404_status() throws Exception {
        doThrow(ReservationNotFoundException.class)
                .when(reservationCancelService)
                .cancelReservation(anyLong(), anyLong());

        mockMvc.perform(patch("/reservations/999"))
                .andExpect(status().isNotFound());
    }

}
