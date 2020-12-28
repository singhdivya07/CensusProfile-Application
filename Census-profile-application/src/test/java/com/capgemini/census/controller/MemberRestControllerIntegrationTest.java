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
import com.capgemini.census.entity.Gender;
import com.capgemini.census.entity.MaritalStatus;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.entity.Relationship;
import com.capgemini.census.repository.MemberInformationRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CensusProfileApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MemberRestControllerIntegrationTest {
	
	 @Autowired
	    private MockMvc mvc;

	    @Autowired
	    private MemberInformationRepository repository;

	    @AfterEach
	    public void resetDb() {
	        repository.deleteAll();
	    }
	    
	    @Test
	    public void whenValidInput_thenCreateEmployee() throws Exception {
	    	MemberInformation member = new MemberInformation(1,"meena","Sheth",22
	        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);
	        
	        mvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(member)));

	        List<MemberInformation> found = repository.findAll();
	        assertThat(found).extracting(MemberInformation::getFirstName).containsOnly("meena");
	    }

	    
	    @Test
	    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
	        createTestmember("Rucha","Sheth",22
	        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);
	        createTestmember("Kalyani","Shinde",26
	        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);
	        
	        mvc.perform(get("/api/member").contentType(MediaType.APPLICATION_JSON))
	          .andDo(print())
	          .andExpect(status().isOk())
	          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
	          .andExpect(jsonPath("$[0].firstName", is("Rucha")))
	          .andExpect(jsonPath("$[1].firstName", is("Kalyani")));
	        
	    }

	   private void createTestmember(String firstName, String lastName, int age, Gender gender, String educationDetails,
				MaritalStatus maritalStatus, Relationship relationship) {
	        MemberInformation mem = new MemberInformation(firstName,lastName,age,gender,educationDetails,maritalStatus,relationship);
	        repository.saveAndFlush(mem);
	    }    
	    

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
