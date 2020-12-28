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

import com.capgemini.census.entity.Application;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.entity.User;
import com.capgemini.census.exception.ApplicationException;
import com.capgemini.census.exception.UserException;
import com.capgemini.census.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class UserController {
	
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
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUserDeatils() {
		try {
			List<User> userList = userService.getAllUserDeatils();
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (UserException e) {
			//log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		try {
			User user = userService.getUserById(id);
			//log.info("Product added" + product);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserException e) {
			//log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable Integer id) {
		try {
			Integer status = userService.deleteUserById(id);
			if (status == 1) {
				//log.info("user: " + id + " deleted from database");
				return "user: " + id + " deleted from database";
			} else {
				//log.debug("Unable to delete user from database");
				return "Unable to delete user from database";
			}

		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
	@ApiOperation(value = "Update user",
			consumes = "user object sent as request body",
			response = User.class,
			httpMethod = "PUT")
	@PutMapping("/")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		try {
			User updatedUser = userService.updateUser(user);
			//log.info("Product: " + product.getProductId() + " updated");
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);

		} catch (UserException e) {
		//	log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
