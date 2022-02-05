package com.employee.directory.crud.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.directory.crud.entity.Employee;
import com.employee.directory.crud.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class Employeecontroller
{

	private EmployeeService employeeService;

	public Employeecontroller(@Autowired EmployeeService employeeService)
	{

		this.employeeService = employeeService;

	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		employeeService.save(employee);

		return employee;

	}

	@DeleteMapping("/employees/{employeeId}")
	public Employee deleteEmployee(@PathVariable Long employeeId)
	{
		Employee employee = employeeService.findById(employeeId);

		if (Objects.isNull(employee))
		{
			throw new RuntimeException(
				String.format("This employee id: %s doesn't exist", employeeId));
		}

		employeeService.deleteById(employeeId);

		return employee;

	}

	@GetMapping("/employees")
	public List<Employee> findAll()
	{

		return employeeService.findAll();

	}

	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable Long employeeId)
	{
		Employee employee = employeeService.findById(employeeId);

		if (employee == null)
		{
			throw new RuntimeException(String.format("Employee id: %s not found", employeeId));
		}
		return employee;

	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee)
	{
		employeeService.save(employee);

		return employee;

	}

}
