package com.nnk.springboot.config;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException { //TODO a regarder avec Frank

        User person = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        String redirectURL;


        if (person.getRole().contains("ADMIN")) {
            redirectURL = "/user/list";

        } else if (person.getRole().contains("USER")) {
            redirectURL = "/bidList/list";

        } else {
            redirectURL = "/error";
        }


        response.sendRedirect(redirectURL);

    }

}