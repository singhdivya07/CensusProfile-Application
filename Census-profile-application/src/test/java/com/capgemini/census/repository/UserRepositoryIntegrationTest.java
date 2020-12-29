package com.capgemini.census.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
import com.capgemini.census.entity.Role;
import com.capgemini.census.entity.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CensusProfileApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
@DirtiesContext
public class UserRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void resetDb() {
		userRepository.deleteAll();
	}

	@Test
	public void whenFindById_thenReturnUser() {
		User user = new User("Rucha","Rucha123",Role.ADMIN);
		entityManager.persistAndFlush(user);

		User fromDb = userRepository.findById(user.getUserId()).orElse(null);
		assertThat(fromDb.getUserName()).isEqualTo(user.getUserName());
	}

	@Test
	public void whenInvalidId_thenReturnNull() {
		User fromDb = userRepository.findById(-11).orElse(null);
		assertThat(fromDb).isNull();
	}

	@Test
	public void givenListofEmployees_whenFindAll_thenReturnAllUsers() {

		User user = new User("Rucha","Rucha123",Role.ADMIN);
		User user1 = new User("Kalyani","Kalyani123",Role.ADMIN);
		User user2 = new User("Bhagya","Bhagya123",Role.USER);

		entityManager.persist(user);
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.flush();

		List<User> userList = userRepository.findAll();

		assertThat(userList).hasSize(3).extracting(User::getUserName)
		.containsOnly(user.getUserName(), user1.getUserName(),
				user2.getUserName());
	}
}
