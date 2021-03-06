package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig
{

	@Autowired
	private Environment environment;

	@Bean
	public DataSource securityDataSource()
	{
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try
		{
			securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		}
		catch (PropertyVetoException exception)
		{
			throw new RuntimeException(exception);
		}

		securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		securityDataSource.setUser(environment.getProperty("jdbc.user"));
		securityDataSource.setPassword(environment.getProperty("jdbc.password"));

		securityDataSource.setInitialPoolSize(getPropertyAsInt("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getPropertyAsInt("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getPropertyAsInt("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getPropertyAsInt("connection.pool.maxIdleTime"));

		return securityDataSource;

	}

	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;

	}

	private int getPropertyAsInt(String propertyName)
	{
		return Integer.parseInt(environment.getProperty(propertyName));

	}

}
