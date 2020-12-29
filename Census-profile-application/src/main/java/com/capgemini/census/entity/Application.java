package com.capgemini.census.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Application {
	
	@Id
	@Column(name="application_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer applicationId;

	@JsonIgnore
	@ToString.Include
	@OneToMany(mappedBy = "application")
	//@JsonManagedReference
	private Set<MemberInformation> members;

	@ToString.Exclude
	@ManyToOne (cascade=CascadeType.ALL)
   // @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	@JsonBackReference
    @JoinColumn(name="user_id")
    private User user;

	
}
