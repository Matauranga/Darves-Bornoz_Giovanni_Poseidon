package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rule;
import jakarta.transaction.Transactional;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /rule/list")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void getRuleList() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rule/list"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleList"))
                .andExpect(model().attribute("ruleList", hasSize(4)));
    }

    @DisplayName("Try to perform method get on /rule/add")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void getAddRuleForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rule/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rule"));
    }

    @DisplayName("Try to perform method post on /rule/validate")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void postAddRuleValidate() throws Exception {
        //Given an initial Rule
        Rule rule = new Rule("Add Test Rule", "Test Rule", "Test Json", "Test Template", "Test SQL Str", "Test SQL Part");

        //When we initiate the request
        mockMvc.perform(post("/rule/validate")
                        .flashAttr("rule", rule))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Try to perform method get on /rule/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rule/update/2"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attribute("rule", hasProperty("description", is("Test Description B"))))
                .andExpect(model().attribute("rule", hasProperty("json", is("Test Json B"))))
                .andExpect(model().attribute("rule", hasProperty("name", is("Test Name B"))))
                .andExpect(model().attribute("rule", hasProperty("sqlPart", is("Test SqlPart B"))))
                .andExpect(model().attribute("rule", hasProperty("sqlStr", is("Test SqlStr B"))))
                .andExpect(model().attribute("rule", hasProperty("template", is("Test Templates B"))));
    }

    @DisplayName("Try to perform method post on /rule/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void updateRuleName() throws Exception {
        //Given a Rule updated
        Rule ruleUpdated = new Rule("Test Rule Updated", "Test Rule Updated", "Test Json Updated", "Test Template Updated", "Test SQL Str Updated", "Test SQL Part Updated");

        //When we initiate the request
        mockMvc.perform(post("/rule/update/3")
                        .flashAttr("rule", ruleUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Try to perform method get on /rule/delete/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void deleteRuleName() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/rule/delete/4"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}