package com.spring.ex;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName().trim();
		String password = authentication.getCredentials().toString();
		
		// you can get data from db and compare here
		 if(("user".equals(name) && "password".equals(password))){
	        	System.out.println("Succesful authentication!");
	        	return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());	
	        }
	        
		 System.out.println("Login fail!");
	        return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
