package com.capgemini.census.service;

import java.util.List;

import com.capgemini.census.entity.User;
import com.capgemini.census.exception.UserException;

public interface UserService {
	public User addUser(User user) throws UserException;
	public List<User> getAllUserDeatils() throws UserException;
	public User getUserById(Integer id) throws UserException;
	public Integer deleteUserById(Integer id) throws UserException;
	public User updateUser(User user) throws UserException;
}
