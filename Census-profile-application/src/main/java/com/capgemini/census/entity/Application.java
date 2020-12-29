package com.capgemini.census.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * It contains Application Id which is unique for a family.
 * This class represents the table in the database.
 * @author HP
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name="application")
public class Application {
	
	@Id
	@Column(name="application_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer applicationId;

	@JsonIgnore
	@ToString.Include
	@OneToMany(mappedBy = "application")
	private Set<MemberInformation> members;

	@ToString.Exclude
	@ManyToOne //(cascade=CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	@JsonBackReference
    @JoinColumn(name="user_id")
    private User user;

	public Application() {
		super();
	}

	public Application(Integer applicationId, Set<MemberInformation> members, User user) {
		super();
		this.applicationId = applicationId;
		this.members = members;
		this.user = user;
	}

	public Application(User user) {
		this.user = user;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Set<MemberInformation> getMembers() {
		return members;
	}

	public void setMembers(Set<MemberInformation> members) {
		this.members = members;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", members=" + members + ", user=" + user + "]";
	}
	
	

}
