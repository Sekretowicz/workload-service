package com.sekretowicz.workload_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class WorkloadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkloadServiceApplication.class, args);
	}

}
