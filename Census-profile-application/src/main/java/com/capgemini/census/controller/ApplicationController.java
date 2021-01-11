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
import com.capgemini.census.entity.User;
import com.capgemini.census.exception.ApplicationException;
import com.capgemini.census.service.ApplicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This controller class is responsible for processing 
 * incoming request of the client.
 * Then, the controller invokes a business class to process business-related tasks.
 * 
 * @author HP
 *
 */
@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/application")
public class ApplicationController {
		
		@Autowired
		private ApplicationService applicationService;
		
		/**
		 * This method adds application id into the application table.
		 * @param id This is the unique Id for each family.
		 * @param application This is the object of application entity.
		 */
		@PostMapping("/{id}")
		public void addApplication(@PathVariable Integer id,@RequestBody Application application) 
		{
			try {
				
				applicationService.addApplication(id,application);
			} catch (ApplicationException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}

		/**
		 * This methods returns the application details that exists.
		 * @return Returns the list of application details.
		 */
		@GetMapping("/get/")
		public ResponseEntity<List<Application>> getAllAppDeatils() {
			try {
				List<Application> appList = applicationService.getAllAppDeatils();
				return new ResponseEntity<>(appList, HttpStatus.OK);
			} catch (ApplicationException e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}
		
		/**
		 * This method returns the information for the specific required Id.
		 * @param id It is the unique id of user.
		 * @return It returns the application for the specified Id.
		 */
		@GetMapping("/{id}")
		public ResponseEntity<Application> getApplicationById(@PathVariable Integer id) {
			try {
				Application application = applicationService.getApplicationById(id);
				
				return new ResponseEntity<>(application, HttpStatus.OK);
			} catch (ApplicationException e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}

}
