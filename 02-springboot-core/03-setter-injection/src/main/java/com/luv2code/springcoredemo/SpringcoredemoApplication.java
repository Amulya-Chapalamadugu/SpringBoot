package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//if we run the code with util pkg outside the springcoredemo, spring does not perform component scanning and if run the application gives error
//so we need to Explicitly add or list the base packages to scan
@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
