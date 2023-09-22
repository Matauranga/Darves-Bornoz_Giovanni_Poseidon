package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CrudServiceInterface;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("app")
@RequiredArgsConstructor
public class LoginController {


    private final CrudServiceInterface<User> userService;

    /**
     * Handler method to handle login request
     */
//    @GetMapping("/login")
//    public ModelAndView getLogin() {
//
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/login");
//        return mav;
//    }

    /**
     * Handler method to handle login request
     */
    @GetMapping({"/login"})
    public String getLogin() {
        return "login";
    }

    /**
     * Handler method to handle ????
     *
     * @return the page to ???
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.getAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * Handler method to handle http error
     *
     * @param request that give the error status code
     * @return the error page
     */
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorMsg", true);
                return "403";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { //Todo : not yet used
                return "";
            }
        }
        return "error";
    }
}
