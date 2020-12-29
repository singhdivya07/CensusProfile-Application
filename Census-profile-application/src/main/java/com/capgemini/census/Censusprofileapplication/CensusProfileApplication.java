package com.capgemini.census.Censusprofileapplication;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * This is the main class for the application.
 * This class contains the method that is entry point for the application. 
 * 
 * @author HP
 *
 */
@SpringBootApplication(scanBasePackages = "com.capgemini.census")
@EnableJpaRepositories(basePackages = "com.capgemini.census.repository")
@EntityScan("com.capgemini.census.entity")
public class CensusProfileApplication {
	
/**
 * This method is the entry point to the application.
 * 
 * @param args
 */
	public static void main(String[] args) {
		SpringApplication.run(CensusProfileApplication.class, args);
	}

	@Bean
	  public Docket openApiEmployeeStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-census-profile-application")
	        .select()
	        .paths(productPaths())
	        .build();
	  }

	  private Predicate<String> productPaths() {
	    return regex(".*/api/.*");
	  }
	
}
