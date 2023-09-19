package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /bidList/list")
    @Test
    void home() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk());

    }

    @DisplayName("Try to perform method get on /bidList/add")
    @Test
    void addBidForm() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());
    }

    @DisplayName("Try to perform method post on /bidList/validate")
    @Test
    void validate() throws Exception {
        //Given an initial BidList
        BidList bid = new BidList("Account Test", "Type Test", 10d);

        //When we initiate the request
        mockMvc.perform(post("/bidList/validate")
                        .flashAttr("bidList", bid))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method get on /bidList/update/{id}")
    @Test
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bidList/update/-1"))

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }


    @Test
    void updateBid() throws Exception {
        //Given an BidList updated
        BidList bidUpdated = new BidList("Account Test Updated", "Type Test Updated", 20d);

        //When we initiate the request
        mockMvc.perform(post("/bidList/update/-1")
                        .flashAttr("bidList", bidUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @Test
    void deleteBid() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/bidList/delete/-2"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}