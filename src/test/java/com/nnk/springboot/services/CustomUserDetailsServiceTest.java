package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @InjectMocks
    CustomUserDetailsService customUserDetailsService;
    @Mock
    UserRepository userRepository;

    @DisplayName("Try to load a user connected and authority")
    @Test
    void loadUserByUsernameAndGetAuthority() {
        //Given a user
        User user = new User("UserNameTest", "TEST", "FullNameTest", "USER");

        //When we try to load information
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.of(user));
        UserDetails response = customUserDetailsService.loadUserByUsername(user.getUsername());

        //Then we verify if this have works correctly
        verify(userRepository, times(2)).findUserByUsername(any());
        assertThat(response.getUsername()).isEqualTo(user.getUsername());
        assertThat(response.getPassword()).isEqualTo(user.getPassword());
        assertThat(response.getAuthorities().toString()).isEqualTo("[" + user.getRole() + "]");//TODO :Expected :"USER" --> Actual :"[USER]"
    }

    @DisplayName("Try to load a user connected")//TODO : mock getAuthorities
    @Test
    @WithUserDetails
    void loadUserByUsername() {
//        //Given a user
//        User user = new User("UserNameTest", "TEST", "FullNameTest", "USER");
//
//        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
//
//        //When we try to load information
//        when(userRepository.findUserByUsername(any())).thenReturn(Optional.of(user));
//        when(customUserDetailsService.getAuthorities(any())).thenReturn(authorities);
//        UserDetails response = customUserDetailsService.loadUserByUsername(user.getUsername());
//
//
//        //Then we verify if this have works correctly
//        verify(userRepository, times(2)).findUserByUsername(any());
//        assertThat(response.getUsername()).isEqualTo(user.getUsername());
//        assertThat(response.getPassword()).isEqualTo(user.getPassword());
    }

    @DisplayName("Try to load a user with wrong information")
    @Test
    void loadUserByUsernameNotFound() {
        //Given a user
        User user = new User();

        //When we try to load information
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(user.getUsername())).getMessage();

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findUserByUsername(any());
        assertThat(errMsg).contains("Invalid username or password.");
    }

    @DisplayName("Try to get authority of a user")
    @Test
    void getAuthorities() {
        //Given a user
        User user = new User("UserNameTest", "TEST", "FullNameTest", "ADMIN");

        //When we try to load information
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.of(user));
        Collection<? extends GrantedAuthority> response = customUserDetailsService.getAuthorities(user.getUsername());

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findUserByUsername(any());
        assertThat(response.stream().toList().toString()).isEqualTo("[" + user.getRole() + "]");

    }

    @DisplayName("Try to get authority of a user not found")
    @Test
    void getAuthoritiesNotFound() {
        //Given a user
        User user = new User();

        //When we try to load authority
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.empty());
        String errMsg = assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.getAuthorities(user.getUsername())).getMessage();

        //Then we verify if this have works correctly
        verify(userRepository, times(1)).findUserByUsername(any());
        assertThat(errMsg).contains("Invalid username or password.");
    }
}