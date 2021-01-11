package com.capgemini.census.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.census.entity.LogOutPayload;
import com.capgemini.census.entity.User;
import com.capgemini.census.exception.BaseResponse;
import com.capgemini.census.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * This controller class is responsible for processing 
 * incoming request of the client.
 * Then, the controller invokes a business class to process business-related tasks
 * 
 * @author HP
 *
 */

@RestController

@RequestMapping("/api")
@Api(value = "User")   
public class LoginController {



	@Autowired 
	private LoginService loginService;


/**
 * Sign In function is performed by this method.
 * @param User Object is passed to validate sign in.
 * @return Returns the response whether the sign in was successful or not.
 */

	@PostMapping("/login") 
	@ApiOperation(value = "SignIn")
	public ResponseEntity<?> signIn( @RequestBody User user) {
		String str = loginService.signIn(user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}



	/**
	 * Sign out is performed by this method.
	 * @param user 
	 * @return Returns the response whether the signout was successful or not.
	 */
	@PostMapping("/logout") 
	@ApiOperation(value = "SignOut")
	public ResponseEntity<?> signOut( @RequestBody LogOutPayload user) {
		String str = loginService.signOut(user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}


	/**
	 * This method resets the password for login.
	 * @param user the Id for which you want to reset the password.
	 * @param new_password The new password , user wants to set.
	 * @return Returns the message whether the password was successfully reseted or not.
	 */

	@PostMapping("/reset")
	@ApiOperation(value = "Reset Password")
	public ResponseEntity<?> changePassword( @RequestBody User user, String new_password) {
		String str =loginService.changePassword(user, new_password);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}



}