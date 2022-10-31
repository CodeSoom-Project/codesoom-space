package com.codesoom.myseat.controllers.signup;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SignupRequest;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.auth.SignupService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignupController.class)
class SignupControllerTest {
    private static final Long ID = 1L;
    private static final String NAME = "테스터";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "1111";
    private static final String ENCODED_PASSWORD
            = "$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignupService signupService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationService authService;

    @Test
    @DisplayName("회원 가입 요청 테스트")
    void test() throws Exception {
        //given
        SignupRequest request = SignupRequest.builder()
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        given(signupService.createUser(NAME, EMAIL, PASSWORD))
                .will(invocation -> User.builder()
                        .id(ID)
                        .name(NAME)
                        .email(EMAIL)
                        .password(ENCODED_PASSWORD)
                        .build());

        // when
        ResultActions subject = mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        subject.andExpect(status().isNoContent());
    }

    private String toJson(
            Object object
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
