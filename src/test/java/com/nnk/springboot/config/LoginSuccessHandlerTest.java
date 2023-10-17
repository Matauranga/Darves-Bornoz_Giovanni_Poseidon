package com.nnk.springboot.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class LoginSuccessHandlerTest {

    @InjectMocks
    LoginSuccessHandler loginSuccessHandler;
    @Mock
    Authentication authentication;
    @Mock
    HttpServletResponse response;


    @DisplayName("Test authentication success for user authority ")
    @Test
    void onAuthenticationSuccessUser() throws IOException {
        //Given a user authority
        Collection grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("USER"));
        String redirectURL = "/bid/list";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //When we try to redirect correctly
        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);
        loginSuccessHandler.onAuthenticationSuccess(null, response, authentication);

        //Then we verify if this have works correctly
        verify(response).sendRedirect(captor.capture());
        assertThat(captor.getValue()).isEqualTo(redirectURL);
    }


    @DisplayName("Test authentication success for admin authority ")
    @Test
    void onAuthenticationSuccessAdmin() throws IOException {
        //Given a user
        Collection grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("ADMIN"));
        String redirectURL = "/user/list";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //When we try to redirect correctly
        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);
        loginSuccessHandler.onAuthenticationSuccess(null, response, authentication);

        //Then we verify if this have works correctly
        verify(response).sendRedirect(captor.capture());
        assertThat(captor.getValue()).isEqualTo(redirectURL);

    }

    @DisplayName("Test authentication for role not found")
    @Test
    void onAuthenticationSuccessRoleNotFound() throws IOException {
        //Given a user
        Collection grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("NONE"));
        String redirectURL = "/403";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //When we try to redirect correctly
        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);
        loginSuccessHandler.onAuthenticationSuccess(null, response, authentication);

        //Then we verify if this have works correctly
        verify(response).sendRedirect(captor.capture());
        assertThat(captor.getValue()).isEqualTo(redirectURL);

    }

}