package com.nnk.springboot.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * Method to redirect the user to the correct "home page" based on their authority
     *
     * @param request        information in the request send
     * @param response       information returned by the server
     * @param authentication information about the connected person
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String redirectURL;
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        if (roles.contains("ADMIN")) {
            redirectURL = "/user/list";

        } else if (roles.contains("USER")) {
            redirectURL = "/bid/list";

        } else {
            redirectURL = "/403";
        }

        response.sendRedirect(redirectURL);
    }

}