package com.employee.directory.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.directory.crud.dao.EmployeeDao;
import com.employee.directory.crud.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService
{

	private EmployeeDao employeeDao;

	public EmployeeServiceImpl(@Autowired @Qualifier("employeeDaoJpaImpl") EmployeeDao employeeDao)
	{
		this.employeeDao = employeeDao;

	}

	@Override
	@Transactional
	public void deleteById(Long id)
	{
		employeeDao.deleteById(id);

	}

	@Override
	public List<Employee> findAll()
	{
		return employeeDao.findAll();

	}

	@Override
	public Employee findById(Long id)
	{
		return employeeDao.findById(id);

	}

	@Override
	@Transactional
	public void save(Employee employee)
	{
		employeeDao.save(employee);

	}

}
