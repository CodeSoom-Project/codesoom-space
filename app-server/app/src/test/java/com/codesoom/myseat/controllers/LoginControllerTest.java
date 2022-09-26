//package com.codesoom.myseat.controllers;
//
//import com.codesoom.myseat.domain.User;
//import com.codesoom.myseat.dto.LoginRequest;
//import com.codesoom.myseat.services.UserService;
//import com.codesoom.myseat.utils.JwtUtil;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import javax.transaction.Transactional;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(LoginController.class)
//@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.codesoom-myseat.site")
//class LoginControllerTest {
//    private static final Long USER_ID = 1L;
//    private static final String USER_NAME = "코드숨";
//    private static final String USER_EMAIL = "test@example.com";
//    private static final String USER_PASSWORD = "1234";
//    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9" +
//            ".eyJ1c2VySWQiOjF9" +
//            ".ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService service;
//    
//    @MockBean
//    private JwtUtil jwtUtil;
//    
//    @MockBean
//    private AuthenticationManager authManager;
//
//    private LoginRequest request;
//    private User user;
//
//    @Test
//    @DisplayName("로그인 테스트")
//    void test() throws Exception {
//        // given
//        request = LoginRequest.builder()
//                .email(USER_EMAIL)
//                .password(USER_PASSWORD)
//                .build();
//
//        user = User.builder()
//                .id(USER_ID)
//                .email(USER_EMAIL)
//                .password(USER_PASSWORD)
//                .name(USER_NAME)
//                .build();
//
//        given(service.loadUserByUsername(eq(USER_EMAIL)))
//                .willReturn(user);
//
//        // when
//        ResultActions subject = mockMvc.perform(post("/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson(request)));
//
//        // then
//        subject.andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value(TOKEN));
//
//        // docs
//        subject.andDo(document("login",
//                preprocessRequest(prettyPrint()),
//                preprocessResponse(prettyPrint()),
//                requestFields(
//                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
//                ),
//                responseFields(
//                        fieldWithPath("token").type(JsonFieldType.STRING).description("토큰")
//                )
//        ));
//    }
//
//    private String toJson(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(object);
//    }
//}
