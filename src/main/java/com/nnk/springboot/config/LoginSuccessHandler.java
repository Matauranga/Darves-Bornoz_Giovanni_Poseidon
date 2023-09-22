package com.nnk.springboot.config;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.NotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException { //TODO à regarder avec Frank

        String redirectURL;
        User user = userRepository.findUserByUsername(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found."));

        if (user.getRole().contains("ADMIN")) {
            redirectURL = "/user/list";

        } else if (user.getRole().contains("USER")) {
            redirectURL = "/bidList/list";

        } else {
            redirectURL = "/error";
        }

        response.sendRedirect(redirectURL);
    }

}