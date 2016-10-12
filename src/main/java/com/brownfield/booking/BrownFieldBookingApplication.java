package com.brownfield.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BrownFieldBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrownFieldBookingApplication.class, args);
	}
}
