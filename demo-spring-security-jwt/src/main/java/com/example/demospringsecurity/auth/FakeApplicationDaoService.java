package com.example.demospringsecurity.auth;

import static com.example.demospringsecurity.security.ApplicationUserRole.ADMIN;
import static com.example.demospringsecurity.security.ApplicationUserRole.ADMIN_TRAINEE;
import static com.example.demospringsecurity.security.ApplicationUserRole.STUDENT;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.google.common.base.Objects;

@Repository("fake")
public class FakeApplicationDaoService implements ApplicationUserDao {

  private final PasswordEncoder passwordEncoder;

  public FakeApplicationDaoService(@Autowired PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;

  }

  @Override
  public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
    return Optional.of(getApplicationUsers().stream()
        .filter(applicationUser -> Objects.equal(applicationUser.getUsername(), username))
        .findFirst().orElseThrow(() -> new UsernameNotFoundException(
            String.format("Username %s is not found", username))));
  }

  private List<ApplicationUser> getApplicationUsers() {
    return List.of(
        new ApplicationUser(STUDENT.getGrantedAuthorities(), true, true, true, true,
            passwordEncoder.encode("password"), "annasmith"),
        new ApplicationUser(ADMIN.getGrantedAuthorities(), true, true, true, true,
            passwordEncoder.encode("password"), "linda"),
        new ApplicationUser(ADMIN_TRAINEE.getGrantedAuthorities(), true, true, true, true,
            passwordEncoder.encode("password"), "tom"));
  }

}
