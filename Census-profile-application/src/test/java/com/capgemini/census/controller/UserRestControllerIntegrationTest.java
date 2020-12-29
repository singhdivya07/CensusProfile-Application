package com.capgemini.census.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.census.Censusprofileapplication.CensusProfileApplication;
import com.capgemini.census.entity.Role;
import com.capgemini.census.entity.User;
import com.capgemini.census.repository.UserRepository;

/**
 * This application tests the working of functions for User Controller.
 * @author HP
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CensusProfileApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRestControllerIntegrationTest {
	
	 @Autowired
	    private MockMvc mvc;

	    @Autowired
	    private UserRepository repository;

	    @AfterEach
	    public void resetDb() {
	        repository.deleteAll();
	    }
	    
	    @Test
	    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
	    	User user = new User("Rucha","Rucha123",Role.ADMIN);
	        mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(user)));

	        List<User> found = repository.findAll();
	        assertThat(found).extracting(User::getUserName).containsOnly("Rucha");
	    }

	    
	    @Test
	    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
	        createTestUser("bob","Bob123",Role.ADMIN);
	        createTestUser("alex","A123",Role.USER);

	        
	        mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON))
	          .andDo(print())
	          .andExpect(status().isOk())
	          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
	          .andExpect(jsonPath("$[0].userName", is("bob")))
	          .andExpect(jsonPath("$[1].userName", is("alex")));
	        
	    }

	   private void createTestUser(String userName,String password,Role role) {
	        User emp = new User(userName,password,role);
	        repository.saveAndFlush(emp);
	    }    
	    

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
