package com.nnk.springboot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class LoginController {

    /**
     * Handler method to handle login request
     */
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    /**
     *
     */
    @GetMapping("/secure/article-details")
    public String getAllUserArticles(Model model) {
        return "redirect:/user/list";
    }

}
