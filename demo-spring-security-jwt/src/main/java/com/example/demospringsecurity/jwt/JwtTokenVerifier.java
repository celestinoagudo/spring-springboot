package com.example.demospringsecurity.jwt;

import static com.example.demospringsecurity.jwt.Constant.AUTHORITIES;
import static com.example.demospringsecurity.jwt.Constant.AUTHORITY;
import static java.util.stream.Collectors.toSet;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;


public class JwtTokenVerifier extends OncePerRequestFilter {

  private final JwtConfig jwtConfig;
  private final SecretKey secretKey;

  public JwtTokenVerifier(JwtConfig jwtConfig, SecretKey secretKey) {
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    // executed per request
    String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

    final String tokenPrefix =
        jwtConfig.getTokenPrefix().substring(0, jwtConfig.getTokenPrefix().indexOf("%s"));

    // first phase of validation
    if (!isTokenValid(authorizationHeader, tokenPrefix)) {

      filterChain.doFilter(request, response);
      return;
    }

    // second phase validation
    String token = authorizationHeader.replace(tokenPrefix, "");
    try {

      Jws<Claims> claimsJws =
          Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
      Claims body = claimsJws.getBody();
      String username = body.getSubject();
      @SuppressWarnings("unchecked")
      List<Map<String, String>> authorities = (List<Map<String, String>>) body.get(AUTHORITIES);

      Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
          .map(authority -> new SimpleGrantedAuthority(authority.get(AUTHORITY))).collect(toSet());

      Authentication authentication =
          new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);

      SecurityContextHolder.getContext().setAuthentication(authentication);

    } catch (JwtException exception) {
      throw new RuntimeException(String.format("Token %s cannot be trusted", token));
    }
    // pass the request and response in the next filter in the chain
    filterChain.doFilter(request, response);

  }

  private boolean isTokenValid(String authorizationHeader, String tokenPrefix) {
    return !Strings.isNullOrEmpty(authorizationHeader)
        || authorizationHeader.startsWith(tokenPrefix);
  }

}
