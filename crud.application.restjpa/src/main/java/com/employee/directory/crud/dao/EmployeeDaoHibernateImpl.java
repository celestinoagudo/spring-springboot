package com.employee.directory.crud.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.directory.crud.entity.Employee;


@Repository
public class EmployeeDaoHibernateImpl implements EmployeeDao
{
	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoHibernateImpl(EntityManager entityManager)
	{
		this.entityManager = entityManager;

	}

	@Override
	public void deleteById(Long employeeId)
	{
		Session currentSession = entityManager.unwrap(Session.class);

		Query<?> query = currentSession.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", employeeId);

		query.executeUpdate();

	}

	@Override
	public List<Employee> findAll()
	{
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);

		return query.getResultList();

	}

	@Override
	public Employee findById(Long id)
	{
		Session currentSession = entityManager.unwrap(Session.class);

		return currentSession.get(Employee.class, id);

	}

	@Override
	public void save(Employee employee)
	{
		Session currentSession = entityManager.unwrap(Session.class);

		//if id=0 then save/insert else update
		currentSession.saveOrUpdate(employee);

	}

}
