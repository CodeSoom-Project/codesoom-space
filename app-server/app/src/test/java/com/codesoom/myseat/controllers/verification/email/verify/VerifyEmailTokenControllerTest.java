package com.codesoom.myseat.controllers.verification.email.verify;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.dto.VerifyEmailTokenRequest;
import com.codesoom.myseat.exceptions.EmailTokenException;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.verification.email.verify.VerifyEmailTokenService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VerifyEmailTokenController.class)
class VerifyEmailTokenControllerTest {

    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    @MockBean
    private VerifyEmailTokenService service;

    @MockBean
    private AuthenticationService authService;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter(
                        "UTF-8", true))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @DisplayName("이메일 인증 토큰으로 인증에 성공하면 204 no content를 반환한다.")
    @Test
    void POST_verify_email() throws Exception {
        VerifyEmailTokenRequest request
                = new VerifyEmailTokenRequest("lfkd8122lMSD32k");

        mockMvc.perform(post("/verification/email/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @DisplayName("이메일 인증 토큰이 올바르지 않으면 404 not found를 반환한다.")
    @Test
    void POST_verify_email_not_found() throws Exception {
        VerifyEmailTokenRequest request
                = new VerifyEmailTokenRequest("lfkd8122lMSD32k");
        doThrow(EmailTokenException.class)
                .when(service)
                .verifyEmailToken(any());

        mockMvc.perform(post("/verification/email/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @DisplayName("이메일 인증 토큰에 대한 유저 정보를 찾을 수 없으면 " +
            "404 not found를 반환한다.")
    @Test
    void POST_verify_email_with_user_not_found() throws Exception {
        VerifyEmailTokenRequest request
                = new VerifyEmailTokenRequest("lfkd8122lMSD32k");
        doThrow(UserNotFoundException.class)
                .when(service)
                .verifyEmailToken(any());

        mockMvc.perform(post("/verification/email/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

}
