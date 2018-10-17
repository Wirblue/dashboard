package com.bassintag.dashboard.filter;

import com.bassintag.dashboard.configuration.JwtConfiguration;
import com.bassintag.dashboard.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtConfiguration jwtConfiguration;
    private final JwtService       jwtService;

    public JwtAuthorizationFilter(JwtConfiguration jwtConfiguration, AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtConfiguration = jwtConfiguration;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && !authorization.startsWith("Bearer "))
            authorization = null;
        if (authorization == null)
        {
            authorization = request.getParameter("token");
            if (authorization != null)
                authorization = "Bearer " + authorization;
        }
        if (authorization == null ) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(authorization);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String user = jwtService.verifyToken(token);
        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        return null;
    }
}
