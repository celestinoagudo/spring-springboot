package com.example.demospringsecurity.security;

import static com.example.demospringsecurity.security.ApplicationUserPermission.COURSE_READ;
import static com.example.demospringsecurity.security.ApplicationUserPermission.COURSE_WRITE;
import static com.example.demospringsecurity.security.ApplicationUserPermission.STUDENT_READ;
import static com.example.demospringsecurity.security.ApplicationUserPermission.STUDENT_WRITE;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toSet;
import java.util.Set;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum ApplicationUserRole {
  ADMIN(newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)), ADMIN_TRAINEE(
      newHashSet(COURSE_READ, STUDENT_READ)), STUDENT(newHashSet())


  ;

  private final Set<ApplicationUserPermission> permissions;

  ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
    this.permissions = permissions;

  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(toSet());

    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", this.name())));
    return simpleGrantedAuthorities;


  }

  /**
   * @return the permissions
   */
  public synchronized Set<ApplicationUserPermission> getPermissions() {
    return permissions;

  }
}
