package com.capgemini.census.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.census.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
}


