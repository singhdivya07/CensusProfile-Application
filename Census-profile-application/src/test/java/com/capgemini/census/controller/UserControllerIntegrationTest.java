package com.capgemini.census.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.census.Censusprofileapplication.CensusProfileApplication;
import com.capgemini.census.entity.Role;
import com.capgemini.census.entity.User;
import com.capgemini.census.service.UserService;

/**
 * This class tests the UserController Operations.
 * @author HP
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CensusProfileApplication.class)
@AutoConfigureMockMvc 
public class UserControllerIntegrationTest {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostUser_thenCreateUser() throws Exception {
       
    	User user = new User("Rucha","Rucha123",Role.ADMIN);
          	
        given(userService.addUser(Mockito.any())).willReturn(user);

        mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(user))).andExpect(status().isCreated()).andExpect(jsonPath("$.userName", is("Rucha")));
        verify(userService, VerificationModeFactory.times(1)).addUser(Mockito.any());
        reset(userService);
    }

    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {
        
    	 User user = new User("Rucha","Rucha123",Role.ADMIN);
    	 User user1 = new User("Kalyani","Kalyani123",Role.ADMIN);
    	 User user2 = new User("Bhagya","Bhagya123",Role.USER);
	    	
    	
    	List<User> userList = Arrays.asList(user,user1,user2);

        given(userService.getAllUserDeatils()).willReturn(userList);

        mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].userName", is(user.getUserName())))
        .andExpect(jsonPath("$[1].userName",is(user1.getUserName())))
        .andExpect(jsonPath("$[2].userName", is(user2.getUserName())));
        verify(userService, VerificationModeFactory.times(1)).getAllUserDeatils();
        reset(userService);
    }
}
