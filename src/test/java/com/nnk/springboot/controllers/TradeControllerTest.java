package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /trade/list")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getTradeList() throws Exception {
        //Given

        //When
        mockMvc.perform(get("/trade/list"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Trade List")));
    }

    @DisplayName("Try to perform method get on /trade/add")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getAddTradeForm() throws Exception {
        //Given

        //When
        mockMvc.perform(get("/trade/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add New Trade")));
    }

    @DisplayName("Try to perform method post on /trade/validate")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void postAddTradeValidate() throws Exception {
        //Given an initial Trade
        Trade trade = new Trade("Add Test Account", "Add Test Type", 1.0);

        //When we initiate the request
        mockMvc.perform(post("/trade/validate")
                        .flashAttr("trade", trade))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method get on /trade/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/trade/update/2"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Update Trade")))
                .andExpect(content().string(containsString("Test Account B")));
    }

    @DisplayName("Try to perform method post on /trade/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void updateTrade() throws Exception {
        //Given a Trade updated
        Trade tradeUpdated = new Trade("Test Account Updated", "Test Type Updated", 100.0);

        //When we initiate the request
        mockMvc.perform(post("/trade/update/3")
                        .flashAttr("trade", tradeUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method get on /trade/delete/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void deleteTrade() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/trade/delete/4"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}