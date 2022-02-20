package com.example.demospringsecurity.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {
  private static final List<Student> STUDENTS = Arrays.asList(new Student(1L, "James Bond"),
      new Student(2L, "James Cameron"), new Student(3L, "James Hardy"));

  @GetMapping("{studentId}")
  public Student getStudent(@PathVariable("studentId") Long studentId) {

    return STUDENTS.stream().filter(student -> student.getId().equals(studentId)).findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("Id: %s not found", studentId)));

  }

}
