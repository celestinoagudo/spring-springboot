package com.example.demospringsecurity.security;

import static com.example.demospringsecurity.security.ApplicationUserRole.STUDENT;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demospringsecurity.auth.ApplicationUserService;
import com.example.demospringsecurity.jwt.JwtConfig;
import com.example.demospringsecurity.jwt.JwtTokenVerifier;
import com.example.demospringsecurity.jwt.JwtUsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${api-base-path}")
  private String apiBasePath;

  private final ApplicationUserService applicationUserService;

  private final JwtConfig jwtConfig;

  @Value("${management.api-base-path}")
  private String managementApiBasePath;

  private final PasswordEncoder passwordEncoder;

  private final SecretKey secretKey;
  @Value("${no-auth-required.paths}")
  private String whiteList;


  public ApplicationSecurityConfiguration(@Autowired PasswordEncoder passwordEncoder,
      @Autowired ApplicationUserService applicationUserService, @Autowired JwtConfig jwtConfig,
      @Autowired SecretKey secretKey) {
    this.passwordEncoder = passwordEncoder;
    this.applicationUserService = applicationUserService;
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;

  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(applicationUserService);

    return provider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // with JWT
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig,
            secretKey))
        .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey),
            JwtUsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().antMatchers(whiteList.split(",")).permitAll().antMatchers(apiBasePath)
        .hasRole(STUDENT.name()).anyRequest().authenticated();

  }

}
