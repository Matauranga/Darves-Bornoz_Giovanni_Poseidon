package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class LoginController {

    private final CrudServiceInterface<User> userService;

    /**
     * Handler method to handle login request
     */
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    /**
     * Handler method to handle ????
     *
     * @return the page to ???
     */
    @GetMapping("/secure/article-details")
    public String getAllUserArticles(Model model) {
        //model.addAttribute("users", userService.getAll());
        return "redirect:/user/list";
    }

}
