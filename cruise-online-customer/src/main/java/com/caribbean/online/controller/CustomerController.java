package com.caribbean.online.controller;

import org.springframework.web.bind.annotation.RestController;

import com.caribbean.online.model.dto.CustomerDto;
import com.caribbean.online.model.request.CreateCustomerRequest;
import com.caribbean.online.model.request.UpdateCustomerRequest;
import com.caribbean.online.model.response.CustomerDetailsResponse;
import com.caribbean.online.service.CustomerService;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private Environment env;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CustomerService customerService;

	
	@GetMapping("/status")
	public String status() {
		return "customer service is running on port " + env.getProperty("local.server.port");
	}

	
	@GetMapping
	public String getCustomers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "getCustomers was called with page = " + page + ", limit = " + limit + ", sort = " + sort;
	}

	
	@GetMapping(path = "/{customerId}", 
			    produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerDetailsResponse> getCustomer(@PathVariable String customerId) {
		
		CustomerDetailsResponse cust = customerService.getCustomer(customerId);
		if (cust != null) {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerDetailsResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustReq) {

		CustomerDto createCustomerDto = modelMapper.map(createCustReq, CustomerDto.class);
		
		CustomerDto retCust = customerService.createCustomer(createCustomerDto);
		
		CustomerDetailsResponse resp = modelMapper.map(retCust, CustomerDetailsResponse.class);
		
		//return new ResponseEntity<CustomerDetailsResponse>(resp, HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	
	@PutMapping(path = "/{customerId}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<CustomerDetailsResponse> updateCustomer(@PathVariable String customerId, 
			                                               @Valid @RequestBody UpdateCustomerRequest updateCustDetails) {
		CustomerDetailsResponse cust = customerService.updateCustomer(customerId, updateCustDetails);
		
		if (cust != null) {			
			return new ResponseEntity<>(cust, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}

	
	@DeleteMapping(path = "/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.noContent().build();
	}
}
