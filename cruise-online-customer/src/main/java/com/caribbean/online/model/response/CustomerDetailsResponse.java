package com.caribbean.online.model.response;

import lombok.Data;

@Data
public class CustomerDetailsResponse {
	private String customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phoneNumber;

}
