package com.employee.directory.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.directory.crud.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>
{}
