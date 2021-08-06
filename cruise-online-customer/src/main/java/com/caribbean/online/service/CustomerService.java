package com.caribbean.online.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.caribbean.online.model.dto.CustomerDto;
import com.caribbean.online.model.request.UpdateCustomerRequest;
import com.caribbean.online.model.response.CustomerDetailsResponse;

public interface CustomerService extends UserDetailsService {
	
	CustomerDto createCustomer(CustomerDto createDto);
	CustomerDto getCustomerDetailsByEmail(String email);
	
	CustomerDetailsResponse getCustomer(String customerId);
	CustomerDetailsResponse updateCustomer(String customerId, UpdateCustomerRequest updateCustDetails);
	void deleteCustomer(String customerId);
	
}
