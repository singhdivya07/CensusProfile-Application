package com.capgemini.census.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.census.Censusprofileapplication.CensusProfileApplication;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.repository.MemberInformationRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = CensusProfileApplication.class)					

public class MemberInformationExceptionTest {


	@Autowired
	private MemberInformationRepository repository;

	@Test
	public void whenExceptionThrown_thenAssertionSucceeds() {

		Exception exception = assertThrows(NumberFormatException.class, () -> {

			Integer.parseInt("1a");
		});
		String expectedMessage = "For input string"; 
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}


	@Test
	public void whenActivityExceptionThrown_thenAssertionSucceeds() 
	{
		Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()->{
			MemberInformation memberInfo =  new MemberInformation();
			memberInfo.setMemberId(null);
			Integer id = memberInfo.getMemberId();
			memberInfo = repository.getOne(id);
		});
	}
	
	@SuppressWarnings("null")
	@Test
	private void whenInvalidExceptionThrown_thenAssertionSucceeds() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			String firstName=null;
			firstName.length();
		});

	}
	@SuppressWarnings("null")
	@Test
	private void whenInvalid_ExceptionThrown_thenAssertionSucceeds() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			String lastName=null;
			lastName.length();
		});

	}
}