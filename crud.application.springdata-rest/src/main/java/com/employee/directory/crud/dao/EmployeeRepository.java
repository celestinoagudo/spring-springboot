package com.employee.directory.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.employee.directory.crud.entity.Employee;


@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	//http://localhost:8080/api/members?sort=lastName
	//http://localhost:8080/api/members?sort=lastName,desc

}
