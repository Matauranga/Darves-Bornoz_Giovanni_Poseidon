package com.nnk.springboot.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /login")
    @Test
    void getLogin() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/login"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login with username and Password")));
    }

    @DisplayName("je ne sais pas quoi tester") //Todo : ?????
    @Test
    void getAllUserArticles() {
    }

    @DisplayName("")
    @Test
    @WithMockUser(username = "adminForTest ", password = "$2a$10$2AwCI/q1h4XoyPV6c2V9auqiRvJGgI7gtlWVDzUVXZ1h1Ih6tWpeW", authorities = "ADMIN")
    void error() throws Exception { //Todo a voir avec frank
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bidList/list"))

                //Then we verify is all works correctly
                .andExpect(status().isForbidden());
        //  .andExpect(content().string(containsString("Access Denied Exception")));
    }
}