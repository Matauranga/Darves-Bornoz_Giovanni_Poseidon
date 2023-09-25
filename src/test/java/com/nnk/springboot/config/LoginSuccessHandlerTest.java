package com.nnk.springboot.config;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class LoginSuccessHandlerTest {

    @InjectMocks
    LoginSuccessHandler loginSuccessHandler;
    @Mock
    UserRepository userRepository;
    @Mock
    Authentication authentication;
    @Mock
    HttpServletResponse response;


    @DisplayName("Test authentication success for user authority ")
    @Test
    void onAuthenticationSuccessUser() throws IOException {
        //Given a user
        User user = new User("UserNameTest", "TEST", "FullNameTest", "USER");
        String redirectURL = "/bid/list";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //When we try to redirect correctly
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.of(user));
        loginSuccessHandler.onAuthenticationSuccess(null, response, authentication);

        //Then we verify if this have works correctly
        verify(response).sendRedirect(captor.capture());
        assertThat(captor.getValue()).isEqualTo(redirectURL);
    }


    @DisplayName("Test authentication success for admin authority ")
    @Test
    void onAuthenticationSuccessAdmin() throws IOException {
        //Given a user
        User user = new User("UserNameTest", "TEST", "FullNameTest", "ADMIN");
        String redirectURL = "/user/list";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //When we try to redirect correctly
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.of(user));
        loginSuccessHandler.onAuthenticationSuccess(null, response, authentication);

        //Then we verify if this have works correctly
        verify(response).sendRedirect(captor.capture());
        assertThat(captor.getValue()).isEqualTo(redirectURL);

    }

    @DisplayName("Test authentication for role not found")
    @Test
    void onAuthenticationSuccessRoleNotFound() throws IOException {
        //Given a user
        User user = new User("UserNameTest", "TEST", "FullNameTest", "");
        String redirectURL = "/403";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //When we try to redirect correctly
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.of(user));
        loginSuccessHandler.onAuthenticationSuccess(null, response, authentication);

        //Then we verify if this have works correctly
        verify(response).sendRedirect(captor.capture());
        assertThat(captor.getValue()).isEqualTo(redirectURL);

    }

    @DisplayName("Test authentication for user not found")
    @Test
    void onAuthenticationSuccessUserNotFound() throws IOException {
        //Given

        //When we try to redirect correctly
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(NotFoundException.class, () -> loginSuccessHandler.onAuthenticationSuccess(null, response, authentication)).getMessage();

        //Then we verify if this have works correctly
        assertThat(errMsg).contains("User not found.");

    }

}