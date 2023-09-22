package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("app")
@RequiredArgsConstructor
public class LoginController {


    private final CrudServiceInterface<User> userService;

//    @GetMapping("/login")
//    public ModelAndView login() {
//
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/login");
//        return mav;
//    }

    /**
     * Handler method to handle login request
     */
    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.getAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
