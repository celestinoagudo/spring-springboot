package com.example.demospringsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

  private ApplicationUserDao applicationUserDao;

  public ApplicationUserService(
      @Qualifier("fake") @Autowired ApplicationUserDao applicationUserDao) {

    this.applicationUserDao = applicationUserDao;
  }



  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return applicationUserDao.selectApplicationUserByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException(String.format("Username: %s is not found", username)));
  }

}