package com.example.demospringsecurity.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import com.google.common.net.HttpHeaders;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

  private String secretKey;

  private Integer tokenExpirationAfterDays;
  private String tokenPrefix;

  public JwtConfig() {}

  public String getAuthorizationHeader() {
    return HttpHeaders.AUTHORIZATION;
  }

  /**
   * @return the secretKey
   */
  public synchronized String getSecretKey() {
    return secretKey;
  }

  /**
   * @return the tokenExpirationAfterDays
   */
  public synchronized Integer getTokenExpirationAfterDays() {
    return tokenExpirationAfterDays;
  }

  /**
   * @return the tokenPrefix
   */
  public synchronized String getTokenPrefix() {
    return tokenPrefix;
  }

  /**
   * @param secretKey the secretKey to set
   */
  public synchronized void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  /**
   * @param tokenExpirationAfterDays the tokenExpirationAfterDays to set
   */
  public synchronized void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
    this.tokenExpirationAfterDays = tokenExpirationAfterDays;
  }

  /**
   * @param tokenPrefix the tokenPrefix to set
   */
  public synchronized void setTokenPrefix(String tokenPrefix) {
    this.tokenPrefix = tokenPrefix;
  }

}
