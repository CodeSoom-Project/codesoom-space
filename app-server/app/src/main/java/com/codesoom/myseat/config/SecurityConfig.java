package com.codesoom.myseat.config;

import com.codesoom.myseat.filters.AuthenticationFilter;
import com.codesoom.myseat.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationService authService;

    public SecurityConfig(
            AuthenticationService authService
    ) {
        this.authService = authService;
    }

    @Override
    protected void configure(
            HttpSecurity http
    ) throws Exception {
        Filter authFilter 
                = new AuthenticationFilter(authenticationManager(), authService);

        http
                .cors()
                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .disable()
                .headers()
                .addHeaderWriter(
                        new XFrameOptionsHeaderWriter(
                                new WhiteListedAllowFromStrategy(Arrays.asList("localhost"))    // 여기!
                        )
                )
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/signup", "/login", "/seats").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(authFilter)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));;
    }
}
