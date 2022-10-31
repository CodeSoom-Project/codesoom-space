package com.codesoom.myseat.controllers.login;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
    private static final Long ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";
    private static final String ENCODED_PASSWORD
            = "$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW";

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("로그인 성공")
    void login_success() throws Exception {
        //given
        LoginRequest request = LoginRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        User user = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .password(ENCODED_PASSWORD)
                .build();

        given(userService.findByEmail(EMAIL))
                .willReturn(user);

        given(authService.login(user, PASSWORD))
                .willReturn(ACCESS_TOKEN);

        // when
        ResultActions subject = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        subject.andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(ACCESS_TOKEN))
                .andExpect(jsonPath("$.name").value(NAME));
    }

    private String toJson(
            Object object
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
