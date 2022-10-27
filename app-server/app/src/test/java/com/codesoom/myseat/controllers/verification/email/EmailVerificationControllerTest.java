package com.codesoom.myseat.controllers.verification.email;

import com.codesoom.myseat.domain.EmailToken;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.verification.email.EmailVerificationRequestService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmailVerificationController.class)
class EmailVerificationControllerTest {

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @MockBean
    private EmailVerificationRequestService service;

    @MockBean
    private AuthenticationService authService;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter(
                        "UTF-8", true))
                .defaultRequest(
                        post("/verification/email")
                                .header(HttpHeaders.AUTHORIZATION,
                                        "Bearer " + ACCESS_TOKEN))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @DisplayName("이메일 인증 요청이 성공적으로 이루어지면 202 accepted를 반환한다.")
    @Test
    void POST_request_email_verification() throws Exception {
        given(service.requestEmailVerification(anyLong()))
                .willReturn(EmailToken.createEmailToken(1L));
        mockMvc.perform(
                post("/verification/email"))
                .andExpect(
                status().isAccepted());
    }

}
