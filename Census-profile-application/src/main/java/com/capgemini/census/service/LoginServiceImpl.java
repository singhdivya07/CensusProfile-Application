package com.capgemini.census.service;
import static com.capgemini.census.exception.AppConstants.OPERATION_FAILED;
import static com.capgemini.census.exception.AppConstants.USER_NOT_FOUND;
import static com.capgemini.census.exception.AppConstants.WRONG_PASSWORD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.census.entity.LogOutPayload;
import com.capgemini.census.entity.User;
import com.capgemini.census.exception.OperationFailedException;
import com.capgemini.census.exception.ResourceNotFound;
import com.capgemini.census.repository.LoginRepository;
/**
 * This is the Implementation class that provides method to implement the sign In and
 * Sign out operation.
 * 
 * @author HP
 *
 */

//@Service annotation is used to mark the class as a service provider
@Service
public class LoginServiceImpl implements LoginService {

	// LoginServiceimpl should implement all the methods present in LoginService
	// interface

	@Autowired // To get a relation with User repository
	private LoginRepository loginRepository;

	// **************//
	/*
	 * signin the page by Staff using StaffId and the password
	 */
	@Override
	public String signIn(User user) {
		String str = null;
		Optional<User> userObj = loginRepository.findById(user.getUserId());
		if (!userObj.isPresent()) {
			System.out.println(userObj);
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = userObj.get().getPassword();
			if (!pwd.equals(user.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				str = "Sign in sucessfull";
				loginRepository.saveAndFlush(userObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}

	// **************//
	/*
	 * signout the page by Staff using StaffId and the password
	 */
	@Override
	public String signOut(LogOutPayload User) {
		String str = null;
		Optional<User> userObj = loginRepository.findById(1);
		if (!userObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			try {
				str = "Sign Out sucessfull";
				loginRepository.saveAndFlush(userObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}


	// **************//
	/*
	 * retest password
	 */
	@Override
	public String changePassword(User login, String new_password) {
		String str = null;
		Optional<User> login1 = loginRepository.findById(1);
		if (!login1.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = login1.get().getPassword();
			if (!pwd.equals(login.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				login1.get().setPassword(new_password);
				loginRepository.saveAndFlush(login1.get());
				str = "Password changed sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}
}