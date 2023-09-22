package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
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
class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /ruleName/list")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getRuleList() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/ruleName/list"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test Description A")));
    }

    @DisplayName("Try to perform method get on /ruleName/add")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void getAddRuleForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/ruleName/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add New Rule")));
    }

    @DisplayName("Try to perform method post on /ruleName/validate")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void postAddRuleValidate() throws Exception {
        //Given an initial Rule
        RuleName ruleName = new RuleName("Add Test RuleName", "Test RuleName", "Test Json", "Test Template", "Test SQL Str", "Test SQL Part");

        //When we initiate the request
        mockMvc.perform(post("/ruleName/validate")
                        .flashAttr("ruleNames", ruleName))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method get on /ruleName/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/ruleName/update/2"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Update New Rule")))
                .andExpect(content().string(containsString("Test Name B")));
    }

    @DisplayName("Try to perform method post on /ruleName/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void updateRuleName() throws Exception {
        //Given a Rule updated
        RuleName ruleNameUpdated = new RuleName("Test RuleName Updated", "Test RuleName Updated", "Test Json Updated", "Test Template Updated", "Test SQL Str Updated", "Test SQL Part Updated");

        //When we initiate the request
        mockMvc.perform(post("/ruleName/update/3")
                        .flashAttr("ruleNames", ruleNameUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }

    @DisplayName("Try to perform method get on /ruleName/delete/{id}")
    @Test
    @WithMockUser(username = "userForTest", password = "$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS", authorities = "USER")
    void deleteRuleName() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/ruleName/delete/4"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}