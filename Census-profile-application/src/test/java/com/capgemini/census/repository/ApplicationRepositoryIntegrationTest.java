package com.capgemini.census.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
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
import com.capgemini.census.entity.Application;
import com.capgemini.census.entity.Role;
import com.capgemini.census.entity.User;

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
 
     @Test
     public void whenFindById_thenReturnApplication() {
     	User user = new User(101,"Rucha","Rucha123",Role.ADMIN);
     	Application application = new Application(user);
     	
         entityManager.persistAndFlush(application);

         Application fromDb = applicationRepository.findById(application.getApplicationId()).orElse(null);
         assertThat(fromDb.getApplicationId()).isEqualTo(application.getApplicationId());
     }
     
//     @Test
//	    public void whenInvalidId_thenReturnNull() {
//	        User fromDb = userRepository.findById(-11).orElse(null);
//	        assertThat(fromDb).isNull();
//	    }
 }
