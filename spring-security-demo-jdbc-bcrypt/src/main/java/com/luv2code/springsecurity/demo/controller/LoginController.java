package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController
{

	@GetMapping("/showLoginPage")
	public String showAccessDeniedPage()
	{
		return "fancy-login";

	}

	@GetMapping("/accessDenied")
	public String showLoginPage()
	{
		return "access-denied";

	}

}
