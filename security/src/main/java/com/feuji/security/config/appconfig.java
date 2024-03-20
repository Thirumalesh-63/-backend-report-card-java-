package com.feuji.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.feuji.security.repository.Studentrepo;
@Configuration
public class appconfig {
	@Autowired
	private Studentrepo repo;
	//write userdetailsservice,authenticationprovider,authenticationmanager,passwordencoder beans here
		//for example
   @Bean
	public UserDetailsService userdetailsservice() {
		return username -> repo.findByEmail(username);
	}
   @Bean
   public AuthenticationProvider authenticationprovider() {
	   DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
	    authprovider.setUserDetailsService(userdetailsservice());
	    authprovider.setPasswordEncoder(passwordEncoder());
	    return authprovider;
   }
   @Bean
   public AuthenticationManager authenticationmanager(AuthenticationConfiguration config) throws Exception {
	   return config.getAuthenticationManager();
   }
   @Bean
   public PasswordEncoder passwordEncoder() {
	
	return new BCryptPasswordEncoder();
}
   
   
}
