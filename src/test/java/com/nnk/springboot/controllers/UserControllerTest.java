package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Try to perform method get on /user/list")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void getUserList() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())

                //Then we verify is all works correctly
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(6)));
    }

    @DisplayName("Try to perform method get on /user/add")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void addUserForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/user/add"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @DisplayName("Try to perform method post on /user/validate")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void addUser() throws Exception {
        //Given an initial user
        User user = new User("UserNameTest", "Test123!", "FullNameTest", "USER");

        //When we initiate the request
        mockMvc.perform(post("/user/validate")
                        .flashAttr("user", user))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Try to perform method post on /user/validate with not valid object")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void throwNotValidObjectPostAddUser() throws Exception {
        //Given an initial user
        User user = new User("       ", "Test123!", "FullNameTest", "USER");

        //When we initiate the request
        mockMvc.perform(post("/user/validate")
                        .flashAttr("user", user))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }

    @DisplayName("Try to perform method post on /user/validate with password rules not respected")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void addUserWithWrongPassword() throws Exception {
        //Given an initial user
        User user = new User("UserNameTest", "test!", "FullNameTest", "USER");

        //When we initiate the request
        mockMvc.perform(post("/user/validate")
                        .flashAttr("user", user))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }


    @DisplayName("Try to perform method get on /user/update/{id}")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void showUpdateForm() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/user/update/2"))

                //Then we verify is all works correctly
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", hasProperty("fullname", is("User"))))
                .andExpect(model().attribute("user", hasProperty("username", is("user"))))
                //  .andExpect(model().attribute("user", hasProperty("password", is(1.0))))
                .andExpect(model().attribute("user", hasProperty("role", is("USER"))));
    }

    @DisplayName("Try to perform method post on /user/update/{id}")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void updateUser() throws Exception {
        //Given a user updated
        User userUpdated = new User("Test", "Test123!", "Test", "ADMIN");

        //When we initiate the request
        mockMvc.perform(post("/user/update/3")
                        .flashAttr("user", userUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Try to perform method post on /user/update/{id} with not valid object")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void throwNotValidUpdateUser() throws Exception {
        //Given a user updated
        User userUpdated = new User("              ", "Test123!", "Test", "ADMIN");

        //When we initiate the request
        mockMvc.perform(post("/user/update/3")
                        .flashAttr("user", userUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }

    @DisplayName("Try to perform method post on /user/update/{id} with password rules not respected")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void updateUserWrongPassword() throws Exception {
        //Given a user updated
        User userUpdated = new User("Test", "Test", "Test", "ADMIN");

        //When we initiate the request
        mockMvc.perform(post("/user/update/3")
                        .flashAttr("user", userUpdated))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isOk());
    }

    @DisplayName("Try to perform method get on /user/delete/{id}")
    @Test
    @WithMockUser(username = "adminForTest ", authorities = "ADMIN")
    void deleteUser() throws Exception {
        //Given

        //When we initiate the request
        mockMvc.perform(get("/user/delete/4"))
                .andDo(MockMvcResultHandlers.print())

                //Then we verify is all works correctly
                .andExpect(status().isFound());
    }
}