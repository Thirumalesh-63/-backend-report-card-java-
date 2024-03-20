package com.feuji.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.feuji.security.exceptionhandler.CustomAccessDeniedHandler;
import com.feuji.security.exceptionhandler.accessdeniedhandler;

import org.springframework.security.authentication.AuthenticationProvider;
@Configuration
@EnableWebSecurity
public class Securityconfig {
	@Autowired
	private AuthenticationProvider authenticatinprovider;
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;
      
	//generate a securityfilterchain bean here
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/createstudent")
		.permitAll()
		.requestMatchers("/api/loginstudent")
		.permitAll()
		.requestMatchers("/api/calculate")
		.permitAll()
		.requestMatchers("/api/allstudents")
		.hasAuthority("ADMIN")
		.requestMatchers("/admin/marks/{userId}")
		.hasAnyAuthority("ADMIN", "USER")
		.requestMatchers("admin/exam")
		.hasAnyAuthority("ADMIN", "USER")
		.requestMatchers("admin/marksforexamanduser/{userId}/{examName}")
		.hasAnyAuthority("ADMIN", "USER")
		.requestMatchers("/admin/**")
		.hasAuthority("ADMIN") 
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().accessDeniedHandler(accessdeniedhandler())
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticatinprovider)
		.addFilterBefore(jwtauthenticationfilter(), UsernamePasswordAuthenticationFilter.class);
		
		
		
		return http.build();
		
	}
	//generate jwtauthenticationfilter and accessdeniedhandler beans here
	@Bean
	public jwtauthenticationfilter jwtauthenticationfilter()
	{
		return new jwtauthenticationfilter(resolver);
	}
	@Bean
    public AccessDeniedHandler accessdeniedhandler() {
    	return new CustomAccessDeniedHandler();
    }
}
