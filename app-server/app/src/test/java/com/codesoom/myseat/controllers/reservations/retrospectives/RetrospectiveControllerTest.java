package com.codesoom.myseat.controllers.reservations.retrospectives;

import com.codesoom.myseat.controllers.reservations.retrospectives.RetrospectiveController;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveService;
import com.codesoom.myseat.services.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(RetrospectiveController.class)
class RetrospectiveControllerTest {

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
    private RetrospectiveService retrospectiveService;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("POST /retrospectives 요청 시 상태코드 204를 응답한다")
    void retrospective_valid_Token() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();

        given(authService.parseToken(ACCESS_TOKEN))
                .willReturn(1L);

        given(userService.findById(1L))
                .willReturn(mockUser);

        RetrospectiveRequest request = RetrospectiveRequest.builder()
                .content("잘했다.")
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
