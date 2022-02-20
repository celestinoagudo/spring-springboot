package com.example.demospringsecurity.security;

public enum ApplicationUserPermission {
  COURSE_READ("course:read"), COURSE_WRITE("course:write"), STUDENT_READ(
      "student:read"), STUDENT_WRITE("student:write");

  private final String permission;

  ApplicationUserPermission(String permission) {
    this.permission = permission;

  }

  /**
   * @return the permission
   */
  public synchronized String getPermission() {
    return permission;

  }

}
