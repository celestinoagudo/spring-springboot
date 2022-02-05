package com.employee.directory.crud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.directory.crud.entity.Employee;


@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao
{
	private EntityManager entityManager;

	public EmployeeDaoJpaImpl(@Autowired EntityManager entityManager)
	{
		this.entityManager = entityManager;

	}

	@Override
	public void deleteById(Long id)
	{
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", id);
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll()
	{
		Query query = entityManager.createQuery("from Employee");

		return query.getResultList();

	}

	@Override
	public Employee findById(Long id)
	{
		return entityManager.find(Employee.class, id);

	}

	@Override
	public void save(Employee employee)
	{
		Employee saved = entityManager.merge(employee);

		employee.setId(saved.getId());

	}

}
