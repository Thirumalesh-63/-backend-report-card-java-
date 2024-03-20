package com.feuji.security.exceptionhandler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Custom logic to handle access denied scenario
    	 String requestURL = request.getRequestURL().toString();
         String method = request.getMethod();
         String user = request.getRemoteUser();
         System.out.println("5555555555555555555555555555555555555555555555555");
         // Log the information

         // Get user roles (Authorities)
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         List<String> roles = authentication.getAuthorities().stream()
                 .map(GrantedAuthority::getAuthority)
                 .collect(Collectors.toList());

         // Log the information
         System.out.println("Access Denied - URL: " + requestURL + ", Method: " + method +
                 ", User: " + user + ", Roles: " + roles);
         System.out.println("Access Denied - URL: " + requestURL + ", Method: " + method + ", User: " + user);

    	 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
         response.getWriter().write("Access Denied");
    }
}
