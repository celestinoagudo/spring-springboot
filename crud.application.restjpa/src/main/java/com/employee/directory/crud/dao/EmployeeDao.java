package com.employee.directory.crud.dao;

import java.util.List;

import com.employee.directory.crud.entity.Employee;


public interface EmployeeDao
{
	public void deleteById(Long id);

	public List<Employee> findAll();

	public Employee findById(Long id);

	public void save(Employee employee);

}
