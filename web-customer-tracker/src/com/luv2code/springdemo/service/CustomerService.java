package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;


public interface CustomerService
{
	public void deleteCustomer(int id);

	public Customer getCustomerById(int id);

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public List<Customer> searchCustomers(String name);

	public void sortCustomers(String sortingCriterion, List<Customer> customers);

}
