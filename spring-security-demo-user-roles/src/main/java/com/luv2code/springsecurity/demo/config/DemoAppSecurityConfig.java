package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Configuration
@EnableWebSecurity
public class DemoAppSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		@SuppressWarnings("deprecation")
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
			.withUser(
				users.username("mary").password("test123").roles("MANAGER", "EMPLOYEE", "ADMIN"))
			.withUser(users.username("susan").password("test123").roles("ADMIN", "EMPLOYEE"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		/*
		 * 1. Restrict access based on the HTTP Servlet Request
		 * 2. Any request to the application must be authenticated (logged in)
		 * 3. Customizing the form login process
		 * 4. Show our custom form  at the request mapping "/showLoginPage"
		 * 5. Login form should POST data to the URL /authenticateTheUser 
		 * for processing (check user id and password)
		 * 6. Allows us to see the login page without the need to login
		 * 7. Add support to logout
		 */
		http.authorizeRequests().antMatchers("/").hasRole("EMPLOYEE").antMatchers("/leaders/**")
			.hasRole("MANAGER").antMatchers("/systems/**").hasRole("ADMIN").and().formLogin()
			.loginPage("/showLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll()
			.and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/accessDenied");

	}
}
