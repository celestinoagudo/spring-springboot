package com.luv2code.springdemo.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortConstants;


@Service
public class CustomerServiceImpl implements CustomerService
{

	@Autowired
	private CustomerDAO customerDAO;

	@Transactional
	@Override
	public void deleteCustomer(int id)
	{
		customerDAO.deleteCustomer(id);

	}

	@Transactional
	@Override
	public Customer getCustomerById(int id)
	{
		return customerDAO.getCustomerById(id);

	}

	@Transactional
	@Override
	public List<Customer> getCustomers()
	{
		return customerDAO.getCustomers();

	}

	@Transactional
	@Override
	public void saveCustomer(Customer customer)
	{
		customerDAO.saveCustomer(customer);

	}

	@Transactional
	@Override
	public List<Customer> searchCustomers(String name)
	{
		return customerDAO.searchCustomers(name);

	}

	@Override
	public void sortCustomers(String sortingCriterion, List<Customer> customers)
	{

		int sortingCriterionAsInt = Integer.parseInt(sortingCriterion);

		switch (sortingCriterionAsInt)
		{
			case SortConstants.FIRST_NAME:
			{
				Collections.sort(customers, (a, b) -> a.getFirstName().compareTo(b.getFirstName()));
				break;
			}
			case SortConstants.LAST_NAME:
			{
				Collections.sort(customers, (a, b) -> a.getLastName().compareTo(b.getLastName()));
				break;
			}

			default:
			{
				Collections.sort(customers,
					(a, b) -> a.getEmailAddress().compareTo(b.getEmailAddress()));
			}
		}

	}

}
