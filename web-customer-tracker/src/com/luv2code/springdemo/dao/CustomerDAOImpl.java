package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteCustomer(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from Customer where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@Override
	public Customer getCustomerById(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(Customer.class, id);

	}

	@Override
	public List<Customer> getCustomers()
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> query =
			currentSession.createQuery("from Customer order by lastName", Customer.class);
		List<Customer> customers = query.getResultList();

		return customers;

	}

	@Override
	public void saveCustomer(Customer customer)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customer);

	}

	@Override
	public List<Customer> searchCustomers(String name)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);

		if ((name != null) && !name.isEmpty())
		{
			query = currentSession.createQuery(
				"from Customer where lower(firstName) like :name or lower(lastName) like :name",
				Customer.class);
			query.setParameter("name", "%" + name.toLowerCase() + "%");
		}
		return query.getResultList();

	}

}
