package com.clients.enrolment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class EnrolmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrolmentApplication.class, args);
	}

}
