package com.capgemini.census.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.census.entity.Role;
import com.capgemini.census.entity.User;
import com.capgemini.census.exception.UserException;
import com.capgemini.census.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceImplIntegrationTest {

	@TestConfiguration
	static class UserServiceImplTestContextConfiguration{

		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}
	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setUserId(101);
		user.setUserName("Rucha");
		user.setRole(Role.ADMIN);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

	}
	@Test
	public void whenValidId_thenUserShouldBeFound() throws UserException {

		User fromDb = userService.getUserById(101);
		assertThat(fromDb.getUserName()).isEqualTo("Rucha");

		verifyFindByIdIsCalledOnce();
	}

	private void verifyFindByIdIsCalledOnce() {
		Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.anyInt());
		Mockito.reset(userRepository);
	}
}
