
package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Celestino Agudo
 *
 */
@Controller
public class HelloWorldController {

	// need a controller method to show the initial HTML form

	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}

	// need a controller method to process the HTML form

	@RequestMapping("/processForm")
	public String processForm() {

		return "helloworld";
	}

	// create a new controller method to read form data and add data to the
	// model
	@RequestMapping("/processFormVersion2")
	public String letsShoutDude(HttpServletRequest request, Model model) {

		// read the request parameter from the HTML form
		String theName = request.getParameter("studentName");

		// convert the data all caps
		theName = theName.toUpperCase();

		String result = String.format("Yo! %s", theName);

		// create the message
		model.addAttribute("message", result);

		// add message to the model
		return "helloworld";
	}

	// create a new controller method to read form data and add data to the
	// model
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(
			@RequestParam("studentName") String theName, Model model) {

		// convert the data all caps
		theName = theName.toUpperCase();

		String result = String.format("Hey! %s", theName);

		// create the message
		model.addAttribute("message", result);

		// add message to the model
		return "helloworld";
	}

}
