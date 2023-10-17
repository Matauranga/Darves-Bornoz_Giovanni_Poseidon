package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * Handler method to handle home request
     *
     * @return the home page
     */
    @RequestMapping({"/home", "/"})
    public String getHome(Model model) {
        return "home";
    }

    /**
     *
     */
    @RequestMapping("/admin/home")
    public String getAdminHome(Model model) {
        return "redirect:/bid/list";
    }


}
