package com.caribbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CruiseOnlineDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruiseOnlineDiscoveryServerApplication.class, args);
	}

}
