package com.caribbean.online.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caribbean.online.data.CustomerEntity;
import com.caribbean.online.data.CustomerRepository;
import com.caribbean.online.model.dto.CustomerDto;
import com.caribbean.online.model.request.UpdateCustomerRequest;
import com.caribbean.online.model.response.CustomerDetailsResponse;
import com.caribbean.online.service.CustomerService;
import com.caribbean.online.util.Utils;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	Utils util;
	
	Map<String, CustomerDetailsResponse> customers;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CustomerRepository repository;
	
	@Override
	public CustomerDto createCustomer(CustomerDto dto) {
		
		CustomerEntity customer = modelMapper.map(dto, CustomerEntity.class);

		customer.setCustomerId(util.generateCustomerId());		
		customer.setEncryptedPassword(util.encrypt(dto.getPassword()));		
		repository.save(customer);
		
		CustomerDto createdCust = modelMapper.map(customer, CustomerDto.class);
		return createdCust;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CustomerEntity custEntity = repository.findByEmail(username);
		if(custEntity == null) throw new UsernameNotFoundException(username);
				
		return new User(custEntity.getEmail(), custEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public CustomerDto getCustomerDetailsByEmail(String email) {
		CustomerEntity custEntity = repository.findByEmail(email);
		if(custEntity == null) throw new UsernameNotFoundException(email);
		
		return modelMapper.map(custEntity, CustomerDto.class);
	}	
	
	
	@Override
	public CustomerDetailsResponse getCustomer(String customerId) {
		
		if (customers.containsKey(customerId)) {
			return customers.get(customerId);
		} else {
			return null;
		}
	}

	@Override
	public CustomerDetailsResponse updateCustomer(String customerId, UpdateCustomerRequest updateCustDetails) {
		
		if (customers.containsKey(customerId)) {
			CustomerDetailsResponse cust = customers.get(customerId);
			cust.setAddress(updateCustDetails.getAddress());
			cust.setPhoneNumber(updateCustDetails.getPhoneNumber());
			customers.put(customerId, cust);
			
			return customers.get(customerId);
		} else {
		    return null;
		}
	}
	
	@Override
	public void deleteCustomer(String customerId) {
		
		customers.remove(customerId);		
	}

}
