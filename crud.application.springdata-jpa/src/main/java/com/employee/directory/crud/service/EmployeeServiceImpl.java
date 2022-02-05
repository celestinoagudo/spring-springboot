package com.employee.directory.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.directory.crud.dao.EmployeeRepository;
import com.employee.directory.crud.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService
{

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(@Autowired EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;

	}

	@Override
	@Transactional
	public void deleteById(Long id)
	{
		employeeRepository.deleteById(id);

	}

	@Override
	public List<Employee> findAll()
	{
		return employeeRepository.findAll();

	}

	@Override
	public Employee findById(Long id)
	{
		Optional<Employee> employee = employeeRepository.findById(id);

		return employee.isPresent() ? employee.get() : null;

	}

	@Override
	@Transactional
	public void save(Employee employee)
	{
		employeeRepository.save(employee);

	}

}
