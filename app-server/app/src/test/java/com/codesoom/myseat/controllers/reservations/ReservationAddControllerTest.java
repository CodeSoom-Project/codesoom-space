package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.domain.Date;
import com.codesoom.myseat.domain.Plan;
import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.ReservationRequest;
import com.codesoom.myseat.enums.ReservationStatus;
import com.codesoom.myseat.exceptions.AlreadyReservedException;
import com.codesoom.myseat.exceptions.NotReservableDateException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.ReservationAddService;
import com.codesoom.myseat.services.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReservationAddController.class)
class ReservationAddControllerTest {

    private static final String ACCESS_TOKEN 
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private ReservationAddService reservationAddService;

    @Autowired
    private WebApplicationContext context;

    final Long USER_ID = 1L;
    final User MOCK_USER = User.builder()
            .id(USER_ID)
            .name("김철수")
            .email("soo@email.com")
            .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
            .build();

    Date getReservableDate() {
        long plusDays = 0;
        LocalDate now = LocalDate.now();
        if (now.getDayOfWeek().getValue() < 6) {
            plusDays = 6 - now.getDayOfWeek().getValue();
        }
        LocalDate saturday = now.plusDays(plusDays);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new Date(saturday.format(dateFormat));
    }

    Date getNotReservableDate() {
        LocalDate notReservableDate =  LocalDate.now().minusDays(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new Date(notReservableDate.format(dateFormat));
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter(
                        "UTF-8", true))
                .defaultRequest(
                        post("/reservations")
                                .header(HttpHeaders.AUTHORIZATION,
                                        "Bearer " + ACCESS_TOKEN))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("POST /reservations 요청 시 상태코드 204를 응답한다")
    void POST_reservations_responses_status_code_204() throws Exception {
        Date date = getReservableDate();

        ReservationRequest request = ReservationRequest.builder()
                .date(date.getDate())
                .content("책읽기, 코테풀기")
                .build();

        given(reservationAddService.createReservation(MOCK_USER,
                date.getDate(),
                "책읽기, 코테풀기"))
                .willReturn(Reservation.builder()
                        .user(MOCK_USER)
                        .plan(Plan.builder().content("책읽기, 코테풀기").build())
                        .date(date)
                        .status(ReservationStatus.RESERVED)
                        .build());

        mockMvc.perform(post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("이미 예약 내역이 존재하는 방문 일자가 주어지면 상태코드 400을 응답한다")
    void If_already_existing_date_given_it_responses_status_code_400()
            throws Exception {

        Date date = getReservableDate();
        ReservationRequest request = ReservationRequest.builder()
                .date(date.getDate())
                .content("책읽기, 코테풀기")
                .build();

        given(reservationAddService.createReservation(any(), any(), any()))
                .willThrow(AlreadyReservedException.class);

        mockMvc.perform(post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("예약이 불가능한 날짜가 주어지면 상태코드 400을 응답한다")
    void If_not_reservable_date_given_it_responses_status_code_400()
            throws Exception {
        Date notReservableDate = getNotReservableDate();
        ReservationRequest request = ReservationRequest.builder()
                .date(notReservableDate.getDate())
                .content("책읽기, 코테풀기")
                .build();
        given(reservationAddService.createReservation(
                any(), anyString(), anyString()))
                .willThrow(NotReservableDateException.class);

        ResultActions result = mockMvc.perform(post("/reservations")
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
