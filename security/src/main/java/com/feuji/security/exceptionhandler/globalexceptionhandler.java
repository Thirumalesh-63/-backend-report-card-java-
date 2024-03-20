package com.feuji.security.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class globalexceptionhandler {
	
	//genrate a method to handle AuthenticationException exception and put httpstatus as NOT_FOUND and return the exception message
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> usernotfound(AuthenticationException exception) {
		return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("user not found with that email");
	}
	
	//gerate a method to handle usernotfound exception and put httpstatus as NOT_FOUND and return the exception message
	@ExceptionHandler(usernotfound.class)
	public ResponseEntity<String> usernotfound2(usernotfound exception) {
		return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
	}
	//genrate a method to handle BadCredentialsException exception and put httpstatus as BAD_REQUEST and return the exception message
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> badcredentials(BadCredentialsException exception) {
		return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("password wrong");
		
	}
	//genrate a method to handle useralreadyexist exception and put httpstatus as alreadyreported and return the exception message
	@ExceptionHandler(useralreadyexist.class)
	public ResponseEntity<String> useralreadyexists(useralreadyexist exception) {
		return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .body(exception.getMessage());
	}
	//generate a method to handle ExpiredJwtException exception and put httpstatus as alreadyreported and return the exception message
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> expired(ExpiredJwtException exception) {
	   
	    return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .body(exception.getMessage());
	}
   //generate a method to handle UnsupportedJwtException exception and put httpstatus as FORBIDDEN and return the exception message
	@ExceptionHandler(UnsupportedJwtException.class)
	public ResponseEntity<String> unsupported(UnsupportedJwtException exception) {
		return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
	}
	//generate a method to handle MalformedJwtException exception and put httpstatus as FORBIDDEN and return the exception message
	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<String> malformed(MalformedJwtException exception) {
		return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
	}
	//generate a method to handle AccessDeniedException exception and put httpstatus as BAD_REQUEST and return the exception message
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> useralreadyexists(AccessDeniedException exception) {
		return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
	}
    //generate a method to handle DataIntegrityViolationException exception and put httpstatus as BAD_REQUEST
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        if (exception.getMessage().contains("foreign key constraint fails")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Cannot perform the operation due to a foreign key constraint. Delete or update related records in the 'marks' table first.");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Data integrity violation");
    }
    //genearte the method to handle exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		
		if(exception instanceof NullPointerException) {
			if(exception.getMessage().contains("\"userDetails\" is null")) {
				return ResponseEntity
		                .status(HttpStatus.NOT_FOUND)
		                .body("The  user in jwt token is not available in database");
			} 
		}
		return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
	}
	
}
