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
		 * @param application 
		 */
		@PostMapping("/{id}")
		public void addApplication(@PathVariable Integer id,@RequestBody Application application) 
		{
			try {
				//application.setUser(user);
				applicationService.addApplication(id,application);
			} catch (ApplicationException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}

		/**
		 * This methods returns the application details that exists.
		 * @return
		 */
		@GetMapping("/get/")
		public ResponseEntity<List<Application>> getAllAppDeatils() {
			try {
				List<Application> appList = applicationService.getAllAppDeatils();
				return new ResponseEntity<>(appList, HttpStatus.OK);
			} catch (ApplicationException e) {
				//log.error(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}
		
		/**
		 * This method returns the information for the specific required Id.
		 * 
		 */
		@GetMapping("/{id}")
		public ResponseEntity<Application> getProductById(@PathVariable Integer id) {
			try {
				Application application = applicationService.getApplicationById(id);
				//log.info("Product added" + product);
				return new ResponseEntity<>(application, HttpStatus.OK);
			} catch (ApplicationException e) {
				//log.error(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}

		
		/**
		 * The method deletes the record for specific Id given as input.
		 * @param id the id that needs to be deleted is stored in this variable.
		 * @return
		 */
		@DeleteMapping("/{id}")
		public String deleteApplicationById(@PathVariable Integer id) {
			try {
				Integer status = applicationService.deleteApplicationById(id);
				if (status == 1) {
					//log.info("product: " + id + " deleted from database");
					return "application: " + id + " deleted from database";
				} else {
					//log.debug("Unable to delete product from database");
					return "Unable to delete application from database";
				}

			} catch (ApplicationException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}

		/**
		 * This method updates the records.
		 * @param application the record that has to be updated is stored in this variable.
		 * @return
		 */
		
		@ApiOperation(value = "Update application",
				consumes = "application object sent as request body",
				response = User.class,
				httpMethod = "PUT")
		@PutMapping("/")
		public ResponseEntity<Application> updateApplication(@RequestBody Application application) {
			try {
				Application updatedApplication = applicationService.updateApplication(application);
				//log.info("Product: " + product.getProductId() + " updated");
				return new ResponseEntity<>(updatedApplication, HttpStatus.OK);

			} catch (ApplicationException e) {
			//	log.error(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}
}
