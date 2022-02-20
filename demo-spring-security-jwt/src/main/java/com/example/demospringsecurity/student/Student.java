package com.example.demospringsecurity.student;

import java.util.Objects;


public class Student {
  private Long id;

  private String studentName;

  public Student() {

  }

  /**
   * @param id
   * @param studentName
   */
  public Student(Long id, String studentName) {
    super();
    this.id = id;
    this.studentName = studentName;

  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Student other = (Student) obj;
    return Objects.equals(id, other.id) && Objects.equals(studentName, other.studentName);

  }

  /**
   * @return the id
   */
  public synchronized Long getId() {
    return id;

  }

  /**
   * @return the studentName
   */
  public synchronized String getStudentName() {
    return studentName;

  }

  @Override
  public int hashCode() {
    return Objects.hash(id, studentName);

  }

  /**
   * @param id the id to set
   */
  public synchronized void setId(Long id) {
    this.id = id;

  }

  /**
   * @param studentName the studentName to set
   */
  public synchronized void setStudentName(String studentName) {
    this.studentName = studentName;

  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", studentName=" + studentName + "]";

  }

}
