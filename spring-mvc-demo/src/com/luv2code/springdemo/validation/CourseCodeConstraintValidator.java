
package com.luv2code.springdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Celestino Agudo
 *
 */
public class CourseCodeConstraintValidator 
					implements ConstraintValidator<CourseCode, String> {

	private String coursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		//Assign the value from passed in from our annotation
		coursePrefix = theCourseCode.value();
	}
	
	
	/**
	 * @param theCode what user inputs in the HTML form
	 * @param cosntraintValidatorContext for placing additional error messages
	 */
	@Override
	public boolean isValid(String theCode, 
				ConstraintValidatorContext cosntraintValidatorContext) {
		// TODO Auto-generated method stub
		
		return ((theCode == null || theCode.isEmpty()) ? "" : 
						theCode.toUpperCase()).startsWith(coursePrefix);
	}

}
