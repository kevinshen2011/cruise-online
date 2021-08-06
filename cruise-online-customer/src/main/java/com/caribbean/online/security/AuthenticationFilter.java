package com.caribbean.online.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.caribbean.online.model.dto.CustomerDto;
import com.caribbean.online.model.request.LoginRequestModel;
import com.caribbean.online.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	private CustomerService customerService;
	private Environment environment;
	
	public AuthenticationFilter(CustomerService customerService, Environment environment, AuthenticationManager authenticationManager) {
		this.customerService = customerService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			LoginRequestModel cres = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
			
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					                                             cres.getEmail(),
					                                             cres.getPassword(),
					                                             new ArrayList<>()));
			
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			                                HttpServletResponse res,
			                                FilterChain chain,
			                                Authentication auth) throws IOException, ServletException {
		
		String username = ((User) auth.getPrincipal()).getUsername();

		CustomerDto custDto = customerService.getCustomerDetailsByEmail(username);
		
		logger.info("token.expiration time: " + environment.getProperty("token.expiration_time"));
		
		String token = Jwts.builder()
				       .setSubject(custDto.getCustomerId())
				       .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
				       .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				       .compact();
		
		res.addHeader("token", token);
		res.addHeader("customer-id", custDto.getCustomerId());
	}

}
