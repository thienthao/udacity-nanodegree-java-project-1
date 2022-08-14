package com.udacity.jwdnd.course1.cloudstorage.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.udacity.jwdnd.course1.cloudstorage.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class AuthenticationSuccessHandlerConfig implements AuthenticationSuccessHandler {

    @Autowired
    HttpSession session;

    @Autowired
    UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String userName;
        if (authentication.getPrincipal() instanceof Principal) {
            userName = ((Principal) authentication.getPrincipal()).getName();

        } else {
            userName = authentication.getPrincipal().toString();
        }
        session.setAttribute("userId", userMapper.findByUsername(userName).getId());
        response.sendRedirect(request.getContextPath());
    }
}
