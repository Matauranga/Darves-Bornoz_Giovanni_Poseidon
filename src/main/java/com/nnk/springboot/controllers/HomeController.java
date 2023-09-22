package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /**
     * Handler method to handle ????
     *
     * @param model ???
     * @return the page to ????
     */
    @RequestMapping({"/home", "/"})
    public String home(Model model) {
        return "home";
    }

    /**
     * Handler method to handle ????
     *
     * @param model ???
     * @return the page to ????
     */
    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        return "redirect:/bidList/list";
    }


}
