package com.capgemini.census.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import com.capgemini.census.entity.Gender;
import com.capgemini.census.entity.MaritalStatus;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.entity.Relationship;
import com.capgemini.census.service.MemberInformationService;

/**
 * This class tests the MemberController class.
 * @author HP
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CensusProfileApplication.class)
@AutoConfigureMockMvc 
public class MemberControllerIntegrationTest {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private MemberInformationService memberInformationService;

    @BeforeEach
    public void setUp() throws Exception {
    }
    
    
	
    @Test
    public void givenUsers_whenGetMembers_thenReturnJsonArray() throws Exception {
    
    	MemberInformation member = new MemberInformation("Rucha","Sheth",22
        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

	 MemberInformation member1 = new MemberInformation("Kalyani","Shinde",26
        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

	 MemberInformation member2 = new MemberInformation("Bhagyashree","Marekar",24
        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

	    	
    	
    	List<MemberInformation> userList = Arrays.asList(member,member1,member2);

        given(memberInformationService.getAllMemberDeatils()).willReturn(userList);

        mvc.perform(get("/api/member").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].firstName", is(member.getFirstName())))
        .andExpect(jsonPath("$[1].firstName",is(member1.getFirstName())))
        .andExpect(jsonPath("$[2].firstName", is(member2.getFirstName())));
        verify(memberInformationService, VerificationModeFactory.times(1)).getAllMemberDeatils();
        reset(memberInformationService);
    }

}





/*
 * @Test 
 * public void whenPostMember_thenCreateMember() throws Exception {
 * 
 * MemberInformation member = new MemberInformation(1,"Rucha","Sheth",22
 * ,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);
 * 
 * given(memberInformationService.addMember(Mockito.any(),
 * 1)).willReturn(member);
 * 
 * mvc.perform(post("/api/member/1").contentType(MediaType.APPLICATION_JSON).
 * content(JsonUtil.toJson(member)))
 * .andExpect(status().isCreated()).andExpect(jsonPath("$.firstName",
 * is("Rucha")));
 * 
 * verify(memberInformationService, VerificationModeFactory.times(1))
 * .addMember(Mockito.any(),1);
 * 
 * reset(memberInformationService); }
 */