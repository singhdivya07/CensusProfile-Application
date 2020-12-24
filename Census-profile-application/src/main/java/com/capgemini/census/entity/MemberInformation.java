package com.capgemini.census.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "member_information")
public class MemberInformation {
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private Integer age;
	
	private LocalDate dob;
	@Column(name = "education_details")
	private String educationDetails;
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	@Column(name = "marital_status")
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	@Enumerated(EnumType.STRING)
	@Column(name="relationship")
	private Relationship relationship;

	@JsonIgnore
	@JsonBackReference
	@ManyToOne //(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="application_id")  
	@ToString.Exclude
	private Application application;


	public MemberInformation() {

	}


	public MemberInformation(Integer memberId, String firstName, String lastName, Integer age,
			 String educationDetails, MaritalStatus maritalStatus, Relationship relationship) {
		
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		//this.gender = gender;
		//this.dob = dob;
		this.educationDetails = educationDetails;
		this.maritalStatus = maritalStatus;
		this.relationship = relationship;
		
	}


	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(String educationDetails) {
		this.educationDetails = educationDetails;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public Application getApplication() {
		return application;
	}


	public void setApplication(Application application) {
		this.application = application;
	}


	@Override
	public String toString() {
		return "MemberInformation [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", dob=" + dob + ", educationDetails=" + educationDetails
				+ ", maritalStatus=" + maritalStatus + ", relationship=" + relationship + "]";
	}


}
