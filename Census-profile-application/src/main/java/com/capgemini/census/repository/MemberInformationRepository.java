package com.capgemini.census.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.census.entity.MemberInformation;



@Repository
public interface MemberInformationRepository 
					extends JpaRepository<MemberInformation,Integer>{

	public MemberInformation findByLastName(String lastName) ;
	public  MemberInformation findByFirstName(String firstName) ;
	
	
	
	
		
}
