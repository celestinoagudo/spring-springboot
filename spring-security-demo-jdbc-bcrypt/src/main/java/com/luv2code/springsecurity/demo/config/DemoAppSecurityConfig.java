package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class DemoAppSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		//password is fun123
		auth.jdbcAuthentication().dataSource(securityDataSource);

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
