package com.caribbean.online.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateCustomerRequest {
	@NotNull(message="Address cannot be null.")
	@Size(min=20, message="Address should not be less than 20 characters.")
	private String address;
	
	@NotNull(message="Phone number cannot be null.")
	@Size(min=10, max=10, message="Phone number should be exactly 10 characters.")
	private String phoneNumber;

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
