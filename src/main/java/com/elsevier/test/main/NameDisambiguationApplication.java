package com.elsevier.test.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.elsevier.test" })
@SpringBootApplication
public class NameDisambiguationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NameDisambiguationApplication.class, args);
	}

}
