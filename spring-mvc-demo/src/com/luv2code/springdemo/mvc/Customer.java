
package com.luv2code.springdemo.mvc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.luv2code.springdemo.validation.CourseCode;

/**
 * @author Celestino Agudo
 *
 */
public class Customer {

	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	@Min(value = 0, message = "must be greater than or equal to zero")
	@Max(value = 10, message = "must be less than or equal to 10")
	@NotNull(message = "is required")
	private Integer freePasses;

	@Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
	private String postalCode;

	@CourseCode(value="ABC", message="must start with ABC")
	private String courseCode;

	/**
	 * @return the courseCode
	 */
	public synchronized String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode the courseCode to set
	 */
	public synchronized void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the postalCode
	 */
	public synchronized String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public synchronized void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the freePasses
	 */
	public synchronized Integer getFreePasses() {
		return freePasses;
	}

	/**
	 * @param freePasses the freePasses to set
	 */
	public synchronized void setFreePasses(Integer freePasses) {
		this.freePasses = freePasses;
	}

	/**
	 * @return the firstName
	 */
	public synchronized String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public synchronized void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public synchronized String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public synchronized void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
