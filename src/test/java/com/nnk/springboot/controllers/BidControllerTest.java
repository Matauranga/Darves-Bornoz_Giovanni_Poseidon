package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /bid/list")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void getBidList() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bid/list"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(model().attribute("bidList", hasSize(4)));
    }

    @DisplayName("Try to perform method get on /bid/add")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void getAddBidForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bid/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bid"));
    }

    @DisplayName("Try to perform method post on /bid/validate")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void postAddBidValidate() throws Exception {
        //Given an initial Bid
        Bid bid = new Bid("Account Test", "Type Test", 10d);

        //When we initiate the request
        mockMvc.perform(post("/bid/validate")
                        .flashAttr("bid", bid))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Try to perform method post on /bid/validate but have error")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void postAddBidValidateError() throws Exception {
        //Given an initial Bid
        Bid bid = new Bid("     ", "Type Test", 10d);

        //When we initiate the request
        mockMvc.perform(post("/bid/validate")
                        .flashAttr("bid", bid))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(view().name("bid/add"))
                .andExpect(model().attributeExists("bid"));
    }

    @DisplayName("Try to perform method get on /bid/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bid/update/2"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bid"))
                .andExpect(model().attribute("bid", hasProperty("bidQuantity", is(2.0))))
                .andExpect(model().attribute("bid", hasProperty("account", is("Test Account B"))))
                .andExpect(model().attribute("bid", hasProperty("type", is("Test Type B"))));
    }

    @DisplayName("Try to perform method post on /bid/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void updateBid() throws Exception {
        //Given an Bid updated
        Bid bidUpdated = new Bid("Account Test Updated", "Type Test Updated", 20d);

        //When we initiate the request
        mockMvc.perform(post("/bid/update/3")
                        .flashAttr("bid", bidUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Try to perform method post on /bid/update/{id} but have error")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void updateBidError() throws Exception {
        //Given an Bid updated
        Bid bidUpdated = new Bid("", "", 0d);

        //When we initiate the request
        mockMvc.perform(post("/bid/update/3")
                        .flashAttr("bid", bidUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(view().name("bid/update"))
                .andExpect(model().attributeExists("bid"))
                .andExpect(model().attribute("bid", hasProperty("bidListId", is(3))));
    }

    @DisplayName("Try to perform method get on /bid/delete/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void deleteBid() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bid/delete/4"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}