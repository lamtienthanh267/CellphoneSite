package com.project.CellphoneSite.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;



public class OnAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		if(exception instanceof UsernameNotFoundException) {
			UsernameNotFoundException ex = (UsernameNotFoundException) exception;
			System.out.println("Username not found exception: "+ex.getMessage());
		}
		
		System.out.println("autheticationException: "+ exception.getLocalizedMessage());
		System.out.println("authenticationFailureHandler: fail to login");
		
		response.sendRedirect("/login_error");
	}
}
