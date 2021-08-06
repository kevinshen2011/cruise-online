package com.caribbean.online.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerDto implements Serializable {

	private static final long serialVersionUID = -9020115582503038882L;
	
	private String firstName;
	private String lastName;
	private String customerId;
	private String email;
	private String address;
	private String phoneNumber;
	private String password;

}
