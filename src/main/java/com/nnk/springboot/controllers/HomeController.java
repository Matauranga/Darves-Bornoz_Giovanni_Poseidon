package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /**
     * Handler method to handle home request
     *
     * @param model ???
     * @return the home page
     */
    @RequestMapping({"/home", "/"})
    public String getHome(Model model) {
        return "home";
    }

    /**
     * Handler method to handle ????
     *
     * @param model ???
     * @return the page to ????
     */
    @RequestMapping("/admin/home") //Todo : cest quoi ça ?
    public String getAdminHome(Model model) {
        return "redirect:/bid/list";
    }


}
