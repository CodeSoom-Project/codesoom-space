package com.codesoom.myseat.controllers.signup;

import com.codesoom.myseat.controllers.signup.SignupController;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SignupRequest;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.auth.SignupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignupController.class)
@AutoConfigureRestDocs(
        uriScheme = "https", 
        uriHost = "api.codesoom-myseat.site"
)
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

        // docs
        subject.andDo(document("signup",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                )
        ));
    }

    private String toJson(
            Object object
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
