package com.capgemini.census.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.census.Censusprofileapplication.CensusProfileApplication;
import com.capgemini.census.entity.Gender;
import com.capgemini.census.entity.MaritalStatus;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.entity.Relationship;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CensusProfileApplication.class})
@AutoConfigureTestDatabase(replace=Replace.NONE)
/*
 * The @DataJpaTest uses @Transactional under the hood. 
 * A test is wrapped inside a transaction that is rolled back at the end. 
 * This means that when using e.g. Hibernate one needs to pay special 
 * attention to how the tested code is written. 
 * As shown below, a manual flush is indeed required:
 */
@DataJpaTest
public class MemberInformationRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MemberInformationRepository memberInformationRepository;

	@Test
	public void whenFindByFirstName_thenReturnMember() {

		MemberInformation member1 = new MemberInformation("Rucha","Sheth",22
				,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

		entityManager.persistAndFlush(member1);

		List<MemberInformation> found = memberInformationRepository.findByFirstName(member1.getFirstName());
		assertThat(((MemberInformation) found).getFirstName()).isEqualTo(member1.getFirstName());
	}

	@Test
	public void whenInvalidFirstName_thenReturnNull() {
		List<MemberInformation> fromDb =  memberInformationRepository.findByFirstName("doesNotExist");
		assertThat(fromDb).isNull();
	}

	@Test
	public void whenFindByLastName_thenReturnMember() {

		MemberInformation member1 = new MemberInformation("Rucha","Sheth",22
				,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

		entityManager.persistAndFlush(member1);

		List<MemberInformation> found = memberInformationRepository.findByLastName(member1.getLastName());
		assertThat(((MemberInformation) found).getLastName()).isEqualTo(member1.getLastName());
	}

	@Test
	public void whenInvalidLastName_thenReturnNull() {
		List<MemberInformation> fromDb =  memberInformationRepository.findByLastName("doesNotExist");
		assertThat(fromDb).isNull();
	}

	@Test
	public void whenInvalidId_thenReturnNull() {
		MemberInformation fromDb = memberInformationRepository.findById(-11).orElse(null);
		assertThat(fromDb).isNull();
	}

	@Test
	public void whenFindById_thenReturnMember() {
		MemberInformation member = new MemberInformation("Rucha","Sheth",22
				,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

		entityManager.persistAndFlush(member);

		MemberInformation fromDb = memberInformationRepository.findById(member.getMemberId()).orElse(null);
		assertThat(fromDb.getFirstName()).isEqualTo(member.getFirstName());
	}

	@Test
	public void givenListofEmployees_whenFindAll_thenReturnAllMember() {

		MemberInformation member = new MemberInformation("Rucha","Sheth",22
				,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

		MemberInformation member1 = new MemberInformation("Kalyani","Shinde",26
				,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);

		MemberInformation member2 = new MemberInformation("Bhagyashree","Marekar",24
				,Gender.FEMALE,"BE",MaritalStatus.UNMARRIED,Relationship.DAUGHTER);


		entityManager.persist(member);
		entityManager.persist(member1);
		entityManager.persist(member2);
		entityManager.flush();

		List<MemberInformation> memberInfo = memberInformationRepository.findAll();

		assertThat(memberInfo).hasSize(3).extracting(MemberInformation::getFirstName)
		.containsOnly(member.getFirstName(), member1.getFirstName(),
				member2.getFirstName());
	}

}