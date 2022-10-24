package com.codesoom.myseat.controllers.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationAddService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveUpdateService;
import com.codesoom.myseat.services.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RetrospectiveUpdateController.class)
class RetrospectiveUpdateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private ReservationAddService reservationAddService;

    @MockBean
    private UserService userService;

    @MockBean
    private RetrospectiveUpdateService retrospectiveUpdateService;

    private User mockUser;
    private Reservation mockReservation;
    private Retrospective mockRetrospective;

    @BeforeEach
    public void setUp() {
        mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        mockRetrospective.builder().content("잘했다.").build();

        mockReservation = Reservation.builder()
                .id(1L)
                .user(mockUser)
                .retrospective(mockRetrospective)
                .build();

        given(userService.findById(1L)).willReturn(mockUser);

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn(1L);

        given(reservationAddService.findReservation(1L))
                .willReturn(mockReservation);

    }

    @Test
    @DisplayName("회고 내용 수정을 요청하면 204 no content를 응답한다")
    void PUT_responses_status_code_204() throws Exception {
        RetrospectiveRequest request = new RetrospectiveRequest("수정했다.");

        mockMvc.perform(put("/reservations/1/retrospectives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .content(toJson(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("본인 소유가 아닌 예약 id가 회고 수정을 요청하면 403 forbidden를 응답한다")
    void PUT_responses_status_code_403() throws Exception {
        RetrospectiveRequest request = new RetrospectiveRequest("수정했다.");

        doThrow(NotOwnedReservationException.class).when(retrospectiveUpdateService)
                .update(anyLong(), any(User.class), anyString());

        mockMvc.perform(put("/reservations/1000/retrospectives")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    private String toJson(
            Object object
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
