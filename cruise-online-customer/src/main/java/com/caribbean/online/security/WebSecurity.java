package com.caribbean.online.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.caribbean.online.service.CustomerService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private Environment environment;
	
	@Value("${gateway.ip}")
	private String ip;
	
	@Value("${login.path}")
	private String login_path;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		//http.authorizeRequests().antMatchers("/customers/**").permitAll();
		
		http.authorizeRequests().antMatchers("/**").hasIpAddress(ip)
		.and()
		.addFilter(getAuthenticationFilter());
		
		//http.authorizeRequests().anyRequest().hasIpAddress(ip);
		//.and()
		//.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customerService).passwordEncoder(bCryptPasswordEncoder);
		
	}
	
	protected CustomerService getCustomerService() {
		
		return this.customerService;
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(customerService, environment, authenticationManager());
		//authenticationFilter.setAuthenticationManager(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(login_path);
		return authenticationFilter;
	}
	
}
