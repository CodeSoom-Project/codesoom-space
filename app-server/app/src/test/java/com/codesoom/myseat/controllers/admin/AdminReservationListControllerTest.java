package com.codesoom.myseat.controllers.admin;

import com.codesoom.myseat.config.SecurityConfig;
import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.services.admin.AdminReservationListService;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(AdminReservationListController.class)
@WithUserDetails
class AdminReservationListControllerTest {
    private static final String ACCESS_TOKEN
            = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final String ADMIN_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmcmVzaCIsImlhdCI6MTY2NjcxNDk1MSwiZXhwIjoxNjY2ODAxMzUxLCJpZCI6M30.caN5FOKNO2cSTNcYG930sNaDRXh_XHBG_wwSrVqd_3Q";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private AdminReservationListService adminService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .alwaysDo(print())
                .build();

        User mockAdmin = User.builder()
                .id(1L)
                .name("관리자")
                .email("admin@email.com")
                .password(passwordEncoder.encode("$2a$10$nxgZpupbdazaYtyQY/5siOLt9RJBlvGzrX1klaB7xJgEFkD31hqPa"))
                .build();

        given(authService.parseToken(ACCESS_TOKEN)).willReturn(1L);
        given(userService.findById(1L)).willReturn(mockAdmin);

//        given(authService.roles(1L)).willReturn(Arrays.asList(new Role("ADMIN")));
//
//        given(roleRepository.findAllByUserId(1L)).willReturn(Arrays.asList(new Role("ADMIN")));
    }

    @Test
    @DisplayName("요청한 유저가 관리자가 아닌 경우 403으로 응답한다.")
    void GET_admin_with_403_status() throws Exception {
        mockMvc.perform(get("/admin/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + ACCESS_TOKEN))
                .andExpect(status().isForbidden());
    }

//    @Test
//    @DisplayName("요청한 유저가 관리자가 아닌 경우 403으로 응답한다.")
//    void GET_admin_with_203_status() throws Exception {
//        mockMvc.perform(get("/admin/reservation")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + ACCESS_TOKEN))
//                .andExpect(status().isOk());
//    }

}
