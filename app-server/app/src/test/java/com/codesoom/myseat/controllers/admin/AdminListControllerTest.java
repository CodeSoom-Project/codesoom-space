package com.codesoom.myseat.controllers.admin;

import com.codesoom.myseat.config.SecurityConfig;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.services.admin.AdminListService;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = AdminListController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebMvcConfigurer.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
    }
)
class AdminListControllerTest {
    private static final String ACCESS_TOKEN
            = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmcmVzaCIsImlhdCI6MTY2NjY5MjY4MSwiZXhwIjoxNjY2Nzc5MDgxLCJpZCI6M30.udchjJzhd8NLWVZbeX9N4-mVr8GjG0d55wvYKQhdHSw";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleRepository roleRepo;

    @MockBean
    private AdminListService adminService;

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
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("요청한 유저가 관리자가 아닌 경우 403으로 응답한다.")
    void GET_admin_with_403_status() throws Exception {
        mockMvc.perform(get("/admin/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + ACCESS_TOKEN))
                .andExpect(status().isForbidden());
    }

}
