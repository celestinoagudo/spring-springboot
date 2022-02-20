package com.example.demospringsecurity.jwt;

public class UsernamePasswordAuthenticationRequest {

  private String password;
  private String username;

  public UsernamePasswordAuthenticationRequest() {}

  /**
   * @return the password
   */
  public synchronized String getPassword() {
    return password;
  }

  /**
   * @return the username
   */
  public synchronized String getUsername() {
    return username;
  }

  /**
   * @param password the password to set
   */
  public synchronized void setPassword(String password) {
    this.password = password;
  }

  /**
   * @param username the username to set
   */
  public synchronized void setUsername(String username) {
    this.username = username;
  }

}
