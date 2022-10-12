package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.exceptions.AlreadyReservedException;
import com.codesoom.myseat.services.AuthenticationService;
import com.codesoom.myseat.services.SeatReservationService;
import com.codesoom.myseat.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SeatReservationController.class)
class SeatReservationControllerTest {
    private static final String ACCESS_TOKEN 
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private SeatReservationService reservationService;

    @Test
    @DisplayName("POST /reservations 요청 시 상태코드 204를 응답한다")
    void POST_reservations_responses_status_code_204() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        
        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn("soo@email.com");
        
        given(userService.findUser("soo@email.com"))
                .willReturn(mockUser);

        SeatReservationRequest request = SeatReservationRequest.builder()
                .date("2022-10-11")
                .content("책읽기, 코테풀기")
                .build();

        ResultActions result =  mockMvc.perform(post("/reservations")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        result.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("이미 예약 내역이 존재하는 방문 일자가 주어지면 상태코드 400을 응답한다")
    void If_already_existing_date_given_it_responses_status_code_400() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn("soo@email.com");

        given(userService.findUser("soo@email.com"))
                .willReturn(mockUser);

        given(reservationService.createReservation(mockUser, "2022-10-11", "책읽기, 코테풀기"))
                .willThrow(AlreadyReservedException.class);

        SeatReservationRequest request = SeatReservationRequest.builder()
                .date("2022-10-11")
                .content("책읽기, 코테풀기")
                .build();

        ResultActions result = mockMvc.perform(post("/reservations")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        result.andExpect(status().isBadRequest());
    }

    private String toJson(
            Object object
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
