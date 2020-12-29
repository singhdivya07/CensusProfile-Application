package com.capgemini.census.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.census.entity.Gender;
import com.capgemini.census.entity.MaritalStatus;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.entity.Relationship;
import com.capgemini.census.repository.MemberInformationRepository;


@ExtendWith(SpringExtension.class)
public class MemberInformationServiceImplIntegrationTest {
	
	 @TestConfiguration
	    static class MemberInformationServiceImplTestContextConfiguration {
	       
		 @Bean
	     public MemberInformationService memberInformationService() {
	            return new MemberInformationServiceImpl();
	        }
	    }

	    @Autowired
	    private MemberInformationService memberInformationService;

	    @MockBean
	    private MemberInformationRepository memberInformationRepository;

	    @BeforeEach
	    public void setUp() {
	    	
	    	  MemberInformation member = new MemberInformation("Rucha","Sheth",22
	  		        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

	  	     MemberInformation member1 = new MemberInformation("Kalyani","Shinde",26
	  		        		,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

	  	    	 List<MemberInformation> allEmployees = Arrays.asList(member);
	        
	        Mockito.when(memberInformationRepository.findById(member.getMemberId()))
	        .thenReturn(Optional.of(member));

	    }
	    
	   
		@SuppressWarnings("unused")
		private void verifyFindByIdIsCalledOnce() {
			Mockito.verify(memberInformationRepository,
					VerificationModeFactory.times(1)).findById(Mockito.anyInt());
			
			Mockito.reset(memberInformationRepository);
		}
}



/*
 * @Test public void whenValidId_thenMemberShouldBeFound() throws
 * MemberInformationException { MemberInformation fromDb
 * =memberInformationService.getMemberInformationByFirstName("Rucha");
 * assertThat(fromDb.getFirstName()).isEqualTo("Rucha");
 * 
 * verifyFindByIdIsCalledOnce();
 * 
 * }
 */

