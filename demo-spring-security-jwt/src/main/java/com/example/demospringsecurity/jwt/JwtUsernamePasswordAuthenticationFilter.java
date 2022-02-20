package com.example.demospringsecurity.jwt;

import static com.example.demospringsecurity.jwt.Constant.AUTHORITIES;
import static java.sql.Date.valueOf;
import static java.time.LocalDate.now;
import java.io.IOException;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtConfig jwtConfig;
  private final SecretKey secretKey;

  public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
      JwtConfig jwtConfig, SecretKey secretKey) {
    this.authenticationManager = authenticationManager;
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;
  }


  // 1. validating credentials
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    try {
      UsernamePasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
          .readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);

      Authentication authentication = new UsernamePasswordAuthenticationToken(
          authenticationRequest.getUsername(), authenticationRequest.getPassword());

      return authenticationManager.authenticate(authentication);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  /*
   * 2. this will be invoked after the attempt authentication is successful this also generates a
   * token which will be sent back to the client.
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {


    String token = Jwts.builder().setSubject(authResult.getName()) // header part
        .claim(AUTHORITIES, authResult.getAuthorities()).setIssuedAt(new Date()) // body part
        .setExpiration(valueOf(now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
        .signWith(secretKey)// signing part
        .compact();

    // send it back to the client
    response.addHeader(jwtConfig.getAuthorizationHeader(),
        String.format(jwtConfig.getTokenPrefix(), token));
  }

}
