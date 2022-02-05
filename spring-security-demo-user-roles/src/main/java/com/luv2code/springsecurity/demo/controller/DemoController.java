package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DemoController
{

	@GetMapping("/systems")
	public String getAdminsPage()
	{
		return "systems";

	}

	@GetMapping("/")
	public String getHomePage()
	{
		return "home";

	}

	@GetMapping("/leaders")
	public String getLeadersPage()
	{
		return "leaders";

	}

}
