package com.capgemini.census.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.census.Censusprofileapplication.CensusProfileApplication;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CensusProfileApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
@DirtiesContext
public class ApplicationRepositoryIntegrationTest {

	 @Autowired
     private TestEntityManager entityManager;

     @Autowired
     private ApplicationRepository applicationRepository;
 
	
 }