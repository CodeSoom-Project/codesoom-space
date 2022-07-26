package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SignupRequest;
import com.codesoom.myseat.services.SignupService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignupController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.codesoom-myseat.site")
class SignupControllerTest {
    private static final Long USER_ID = 1L;
    private static final String NAME = "코드숨";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "test";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignupService service;

    private SignupRequest request;
    private User user;
    
    @Test
    @DisplayName("회원 가입 요청 테스트")
    void test() throws Exception {
        // given
        request = SignupRequest.builder()
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        user = User.builder()
                .id(USER_ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        given(service.signUp(any(SignupRequest.class))).willReturn(user);
        
        // when
        ResultActions subject = mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));
        
        // then
        subject.andExpect(status().isCreated());
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
