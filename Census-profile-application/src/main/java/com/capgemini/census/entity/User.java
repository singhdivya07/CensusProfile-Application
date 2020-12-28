package com.capgemini.census.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name="user_details")
public class User implements Serializable {   
    private static final long serialVersionUID = 1L;
	
		@Id
		@Column(name="user_id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer userId;

		@Column(name="user_name")
		private String userName;
		
		@Column(name="password")
		private String password;
		
		@Enumerated(EnumType.STRING)
		@Column(name="relationship")
		private Role role;

		
//		@OneToMany(mappedBy = "user")
//		private Set<Application> application;
//	   
		
		public User() {
			super();
		}

		

		public User(Integer userId, String userName, String password, Role role) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.password = password;
			this.role = role;
		}

//		public Set<Application> getApplication() {
//			return application;
//		}
//
//		public void setApplication(Set<Application> application) {
//			this.application = application;
//		}

		public User(String userName, String password, Role role) {
			// TODO Auto-generated constructor stub
			this.userName = userName;
			this.password = password;
			this.role = role;
		}



		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

//		public Application getApplication() {
//			return application;
//		}
//
//		public void setApplication(Application application) {
//			this.application = application;
//		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role
					+ "]";
		}
		
		
}
