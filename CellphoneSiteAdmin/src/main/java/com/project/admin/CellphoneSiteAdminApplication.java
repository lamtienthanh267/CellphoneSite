package com.project.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@EntityScan({"com.project.models.entities"})	// scan JPA entities
@SpringBootApplication
public class CellphoneSiteAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CellphoneSiteAdminApplication.class, args);
	}

}
