package com.capgemini.census.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the entity class that contains user details.
 * It represents table in the database.
 * @author HP
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name="user_details")
public class User implements Serializable {   
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;

	@Column(name="user_name",nullable = false)
	private String userName;

	@Column(name="password",nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private Role role;



	public User(String userName, String password, Role role) {
		
		this.userName = userName;
		this.password = password;
		this.role = role;
	}


}
