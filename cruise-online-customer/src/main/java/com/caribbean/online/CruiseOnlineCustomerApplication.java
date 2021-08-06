package com.caribbean.online;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class CruiseOnlineCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruiseOnlineCustomerApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return modelMapper;
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
