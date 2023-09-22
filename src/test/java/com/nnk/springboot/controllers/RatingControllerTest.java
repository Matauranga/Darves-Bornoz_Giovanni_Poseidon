package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
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
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /rating/list")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getRatingList() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rating/list"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Rating List")));
    }

    @DisplayName("Try to perform method get on /rating/add")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getAddRatingForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rating/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add New Rating")));
    }

    @DisplayName("Try to perform method post on /rating/validate")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void postAddRatingValidate() throws Exception {
        //Given an initial Rating
        Rating rating = new Rating(String.valueOf(10), String.valueOf(10), String.valueOf(10), 10); //TODO :String?? int?? dans entity --> String / dans front --> demande des nombres

        //When we initiate the request
        mockMvc.perform(post("/rating/validate")
                        .flashAttr("ratings", rating))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method get on /rating/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rating/update/1"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Update Rating")))
                .andExpect(content().string(containsString("1")));
    }

    @DisplayName("Try to perform method post on /rating/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void updateRating() throws Exception {
        //Given a Rating updated
        Rating rating = new Rating(String.valueOf(100), String.valueOf(100), String.valueOf(100), 100);

        //When we initiate the request
        mockMvc.perform(post("/rating/update/3")
                        .flashAttr("ratings", rating))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method get on /rating/delete/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void deleteRating() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rating/delete/4"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}