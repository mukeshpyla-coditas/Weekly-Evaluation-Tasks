package com.example.jwtFinal.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Service
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession httpSession = request.getSession();
        Enumeration<String> attributesOfRequest = httpSession.getAttributeNames();
        List<String> attributes = Collections.list(attributesOfRequest);
        for(String attribute : attributes) {
            System.out.println(attribute);
        }


        /*SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        String requestUrl = savedRequest.getRedirectUrl();
        response.sendRedirect(savedRequest.getRedirectUrl().isEmpty() ? "/" : requestUrl);*/
    }
}
