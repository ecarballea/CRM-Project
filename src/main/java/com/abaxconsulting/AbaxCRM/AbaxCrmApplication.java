package com.abaxconsulting.AbaxCRM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans(@ComponentScan("com.abaxconsulting.AbaxCRM.controller"))
@EnableJpaRepositories("com.abaxconsulting.AbaxCRM.repository")
@EntityScan("com.abaxconsulting.AbaxCRM.model")
public class AbaxCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbaxCrmApplication.class, args);
	}

}
