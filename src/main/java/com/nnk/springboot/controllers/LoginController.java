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
    @GetMapping("secure/article-details")
    public String getAllUserArticles(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/list";
    }


    /**
     * Handler method to handle http error
     *
     * @param request that give the error status code
     * @return the error page
     */
   /* @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) { //TODO franck
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorMsg", "Your trying to access to a page without the authority necessary");
                return "403";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            }
        }
        return "error";
    }*/
}
