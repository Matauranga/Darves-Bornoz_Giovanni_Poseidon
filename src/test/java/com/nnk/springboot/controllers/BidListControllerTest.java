package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
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
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /bidList/list")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getBidList() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bidList/list"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test Account A")));
    }

    @DisplayName("Try to perform method get on /bidList/add")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getAddBidForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bidList/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add New Bid")));
    }

    @DisplayName("Try to perform method post on /bidList/validate")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void postAddBidValidate() throws Exception {
        //Given an initial BidList
        BidList bid = new BidList("Account Test", "Type Test", 10d);

        //When we initiate the request
        mockMvc.perform(post("/bidList/validate")
                        .flashAttr("bidList", bid))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method post on /bidList/validate but have error")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void postAddBidValidateError() throws Exception {
        //Given an initial BidList
        BidList bid = new BidList("", "Type Test", 10d);

        //When we initiate the request
        mockMvc.perform(post("/bidList/validate")
                        .flashAttr("bidList", bid))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }

    @DisplayName("Try to perform method get on /bidList/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bidList/update/2"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Update Bid")))
                .andExpect(content().string(containsString("Test Account B")));
    }

    @DisplayName("Try to perform method post on /bidList/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void updateBid() throws Exception {
        //Given an BidList updated
        BidList bidUpdated = new BidList("Account Test Updated", "Type Test Updated", 20d);

        //When we initiate the request
        mockMvc.perform(post("/bidList/update/3")
                        .flashAttr("bidList", bidUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method post on /bidList/update/{id} but have error")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void updateBidError() throws Exception {
        //Given an BidList updated
        BidList bidUpdated = new BidList("", "", 0d);

        //When we initiate the request
        mockMvc.perform(post("/bidList/update/3")
                        .flashAttr("bidList", bidUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }

    @DisplayName("Try to perform method get on /bidList/delete/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void deleteBid() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bidList/delete/4"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}