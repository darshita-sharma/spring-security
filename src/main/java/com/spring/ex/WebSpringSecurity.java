package com.spring.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSpringSecurity  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	    private CustomAuthenticationProvider customAuthenticationProvider;
	 @Autowired
		SuccessHandler successHandler;

		@Autowired
		FailureHandler failureHandler;
	 
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
	                .antMatchers("/", "/home").permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginProcessingUrl("/login")
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .successHandler(successHandler)
	    			.failureHandler(failureHandler)
	    			.failureUrl("/home")
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll();
	    }

	    @Autowired
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(this.customAuthenticationProvider);
	    }
}
