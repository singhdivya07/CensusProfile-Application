package com.capgemini.census.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.census.entity.User;
import com.capgemini.census.exception.UserException;
import com.capgemini.census.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")


/**
 * This controller class is responsible for processing 
 * incoming request of the client.
 * Then, the controller invokes a business class to process business-related tasks
 * 
 * @author HP
 *
 */

public class UserController {
	
	/**
	 * This method adds the user into the record.
	 */
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user) 
	{
		try {
			User saved  = userService.addUser(user);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * This method displays the existing Information about the user.
	 * @return Returns the information about all the users in the database.
	 */
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUserDeatils() {
		try {
			List<User> userList = userService.getAllUserDeatils();
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * This method displays the existing Information about the user, 
	 * for the specified Id.
	 * @param id The Id for which the details are required.
	 * @return Returns the details of the user for the specific Id.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		try {
			User user = userService.getUserById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * This method removes the user from the record, specified by the Id.
	 * @param id The Id for which the user is to be deleted.
	 * @return Returns the status whether the delete operation was successful or not.
	 */
	
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable Integer id) {
		try {
			Integer status = userService.deleteUserById(id);
			if (status == 1) {
				return "user: " + id + " deleted from database";
			} else {
				return "Unable to delete user from database";
			}

		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/**
	 * This method updates user details.
	 * @param user The user object for which details are to be updated.
	 * @return Returns the status whether the update operation was successful or not.
	 */
	@ApiOperation(value = "Update user",
			consumes = "user object sent as request body",
			response = User.class,
			httpMethod = "PUT")
	@PutMapping("/")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		try {
			User updatedUser = userService.updateUser(user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);

		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
