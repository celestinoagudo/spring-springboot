package com.employee.directory.crud.service;

import java.util.List;

import com.employee.directory.crud.entity.Employee;


public interface EmployeeService
{
	public void deleteById(Long id);

	public List<Employee> findAll();

	public Employee findById(Long id);

	public void save(Employee employee);

}
