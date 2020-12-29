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
import com.capgemini.census.entity.User;
import com.capgemini.census.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = CensusProfileApplication.class)					

public class UserExceptionTest {


	@Autowired
	private UserRepository repository;

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
			User user =  new User();
			user.setUserId(null);
			Integer id = user.getUserId();
			user = repository.getOne(id);
		});
	}
	@SuppressWarnings("null")
	@Test
	private void whenInvalidExceptionThrown_thenAssertionSucceeds() {
		Assertions.assertThrows(NullPointerException.class, ()->{
			String userName=null;
			userName.length();
		});

	}
}

