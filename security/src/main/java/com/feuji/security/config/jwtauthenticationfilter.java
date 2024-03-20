package com.feuji.security.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtauthenticationfilter extends OncePerRequestFilter{
	
	
	private HandlerExceptionResolver resolver;
	
	@Autowired
    private UserDetailsService userdetailsservice;
	@Autowired
	private jwtservice jwtservice;
	@Autowired
	public jwtauthenticationfilter(HandlerExceptionResolver resolver) {
		this.resolver=resolver;
		
	}
	@Override 
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// write the code here to extract the token from the request header, validate the token, load the user details and set the authentication in the security context

			String authheader=request.getHeader("Authorization");
			String jwt;
			String userName;
			if(authheader==null || !authheader.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			jwt=authheader.substring(7);
			userName=jwtservice.extractusername(jwt);
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userdetails=userdetailsservice.loadUserByUsername(userName);
				if(jwtservice.isTokenValid(jwt, userdetails)) {
					UsernamePasswordAuthenticationToken authtoken= new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
					authtoken.setDetails(
				            new WebAuthenticationDetailsSource().buildDetails(request)
				        );
				        SecurityContextHolder.getContext().setAuthentication(authtoken);
				}
			}
			
			filterChain.doFilter(request, response);
		}
		catch (ExpiredJwtException e) {
		    resolver.resolveException(request, response, null, e);
		} catch (UnsupportedJwtException | MalformedJwtException e) {
			  resolver.resolveException(request, response, null, e);

		} catch (AccessDeniedException e) {
			  resolver.resolveException(request, response, null, e);
		}  catch (Exception e) {
			  resolver.resolveException(request, response, null, e);
		}
		
		
	}

}
