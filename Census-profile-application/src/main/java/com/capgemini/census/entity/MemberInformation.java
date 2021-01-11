package com.capgemini.census.entity;

import java.time.LocalDate;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * This class represents the table in the database.
 * It contains member specific details.
 * firstName, LastName, age, education_deatils, gender,relationship, marital_status
 * are the attributes it contains.
 * @author HP

 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "member_information")
public class MemberInformation {
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;
	@Column(name = "first_name",nullable = false)
	private String firstName;
	@Column(name = "last_name",nullable = false)
	private String lastName;
	private Integer age;
	
	private LocalDate dob;
	@Column(name = "education_details",nullable = false)
	private String educationDetails;
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	

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

	public MemberInformation(String firstName, String lastName, int age, Gender gender,String educationDetails,
			MaritalStatus maritalStatus, Relationship relationship) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.educationDetails = educationDetails;
		this.maritalStatus = maritalStatus;
		this.relationship = relationship;
	}


}
