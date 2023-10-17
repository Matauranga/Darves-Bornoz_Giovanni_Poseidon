package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
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
class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /curvePoint/list")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void getCurvePointList() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/curvePoint/list"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Curve Point List")));
    }

    @DisplayName("Try to perform method get on /curvePoint/add")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void getAddCurvePointForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/curvePoint/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"));
    }

    @DisplayName("Try to perform method post on /curvePoint/validate")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void validate() throws Exception {
        //Given an initial curvePoint
        CurvePoint curvePoint = new CurvePoint(1, 1.0, 1.0);

        //When we initiate the request
        mockMvc.perform(post("/curvePoint/validate")
                        .flashAttr("curvePoint", curvePoint))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Try to perform method get on /curvePoint/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/curvePoint/update/1"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(model().attribute("curvePoint", hasProperty("curveId", is(1))))
                .andExpect(model().attribute("curvePoint", hasProperty("term", is(10.0))))
                .andExpect(model().attribute("curvePoint", hasProperty("value", is(1.0))));
    }

    @DisplayName("Try to perform method post on /curvePoint/update/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void updateCurvePoint() throws Exception {
        //Given an curvePoint updated
        CurvePoint curvePointUpdated = new CurvePoint(2, 55.0, 55.0);

        //When we initiate the request
        mockMvc.perform(post("/curvePoint/update/2")
                        .flashAttr("curvePoint", curvePointUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection())
        ;
    }

    @DisplayName("Try to perform method post on /curvePoint/update/{id} with not valid object")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void throwNotValidUpdateCurvePoint() throws Exception {
        //Given an curvePoint updated
        CurvePoint curvePointUpdated = new CurvePoint(null, 55.0, 55.0);

        //When we initiate the request
        mockMvc.perform(post("/curvePoint/update/2")
                        .flashAttr("curvePoint", curvePointUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }

    @DisplayName("Try to perform method get on /curvePoint/delete/{id}")
    @Test
    @WithMockUser(username = "userForTest", authorities = "USER")
    void deleteCurvePoint() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/curvePoint/delete/3"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}