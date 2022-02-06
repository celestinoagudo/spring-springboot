package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController
{
	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService)
	{
		this.studentService = studentService;

	}

	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long id)
	{
		studentService.deleteStudent(id);

	}

	@GetMapping
	public List<Student> getStudents()
	{
		return studentService.getStudents();

	}

	@PostMapping
	public void registerNewStudent(@RequestBody StudentRequestModel requestModel)
	{
		Student student = new Student(requestModel);
		studentService.addNewStudent(student);

	}

	@PutMapping
	public void updateStudent(@RequestBody StudentRequestModel requestModel)
	{
		Student student = new Student(requestModel);
		studentService.updateStudent(student);

	}

}
