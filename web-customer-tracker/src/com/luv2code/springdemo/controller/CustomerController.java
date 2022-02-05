package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController
{
	@Autowired
	private CustomerService customerService;

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId)
	{
		customerService.deleteCustomer(customerId);
		return "redirect:/customer/list";

	}

	@GetMapping("/list")
	public String listCustomers(Model model,
		@RequestParam(required = false) String sortingCriterion)
	{
		List<Customer> customers = customerService.getCustomers();

		if ((sortingCriterion != null) && !sortingCriterion.isEmpty())
		{
			customerService.sortCustomers(sortingCriterion, customers);
		}

		model.addAttribute("customers", customers);

		return "list-customers";

	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer)
	{
		customerService.saveCustomer(customer);

		return "redirect:/customer/list";

	}

	@GetMapping("/search")
	public String searchCustomers(@RequestParam("name") String name, Model model)
	{
		List<Customer> theCustomers = customerService.searchCustomers(name);

		model.addAttribute("customers", theCustomers);
		return "list-customers";

	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model)
	{
		//create model attribute to bind the form data
		Customer customer = new Customer();

		model.addAttribute("customer", customer);

		return "customer-form";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model)
	{
		Customer customer = customerService.getCustomerById(customerId);

		model.addAttribute("customer", customer);

		return "customer-form";

	}

}
