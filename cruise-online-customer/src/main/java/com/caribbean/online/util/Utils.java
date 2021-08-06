package com.caribbean.online.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Utils {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String generateCustomerId() {
		return UUID.randomUUID().toString();
	}
	
	public String encrypt(String password) {
		
		return bCryptPasswordEncoder.encode(password);

	}
}
