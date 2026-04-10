package com.example.jwtFinal.filter;

import com.example.jwtFinal.service.CustomUserDetailsService;
import com.example.jwtFinal.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * Steps to build the JWTFilter:
 * 1. Get the Authorization segment from the request header
 * 2. Extract the token from the authorization header
 * 3. Extract the username from the token
 * 4. Validate the token
 * 5. If token is valid, create a UsernamePasswordAuthenticationToken and,
      set the userdetails and request details to the token
 * 6. Attach this token into the SecurityContextHolder(.setAuthentication(authToken))
 * 7. Call the next filter in the filter chain
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Get the header of the request
        String authHeader = request.getHeader("Authorization");
        // 2. Extract the token from the "Authorization" segment of header
        String token = null;
        String username = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            // 3. Extract the username from the token
            username = jwtUtil.extractUsername(token);
        }

        // 4. Validate the token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(token, userDetails)) {
                // 5. create a UsernamePasswordAuthenticationToken and, set the userdetails and request details to the token
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 6. Set the security context holder with respective user details and roles
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            System.out.println("User is successfully authenticated by the JwtFilter...");
        }

        // 7. Call the next filter
        filterChain.doFilter(request, response);
    }
}
