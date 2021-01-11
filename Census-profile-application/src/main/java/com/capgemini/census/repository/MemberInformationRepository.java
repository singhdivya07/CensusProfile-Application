package com.capgemini.census.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.census.entity.MemberInformation;



@Repository
public interface MemberInformationRepository 
					extends JpaRepository<MemberInformation,Integer>{

	public List<MemberInformation> findByLastName(String lastName) ;
	public  List<MemberInformation> findByFirstName(String firstName) ;
		
}
