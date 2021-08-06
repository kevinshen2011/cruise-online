package com.caribbean.online.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCustomerRequest {
	@NotNull(message="First name cannot be null.")
	@Size(min=2, message="First name should not be less than 2 characters.")
	private String firstName;
	
	@NotNull(message="Last name cannot be null.")
	@Size(min=2, message="Last name should not be less than 2 characters.")
	private String lastName;
	
	@NotNull(message="Email cannot be null.")
	@Email
	private String email;
	
	@NotNull(message="Address cannot be null.")
	@Size(min=20, message="Address should not be less than 20 characters.")
	private String address;
	
	@NotNull(message="Phone number cannot be null.")
	@Size(min=10, max=10, message="Phone number should be exactly 10 characters.")
	private String phoneNumber;

	@NotNull(message="Password cannot be null.")
	@Size(min=8, max=16, message="Password must be greater than or equal to 8 characters and less than or equal to 16 characters.")
	private String password;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
