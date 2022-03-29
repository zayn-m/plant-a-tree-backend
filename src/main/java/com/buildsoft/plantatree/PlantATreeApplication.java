package com.buildsoft.plantatree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class PlantATreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantATreeApplication.class, args);
	}

}
