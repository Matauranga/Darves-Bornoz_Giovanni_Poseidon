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

    @DisplayName("Try to perform method get on /app/login")
    @Test
    void getLogin() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/app/login"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login with username and Password")));
    }

    @DisplayName("Test on /app/secure/article-details")
    @Test
    @WithMockUser(username = "adminForTest", authorities = "ADMIN")
    void getAllUserArticles() throws Exception {

        mockMvc.perform(get("/app/secure/article-details"))

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

}