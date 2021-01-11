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

import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.exception.MemberInformationException;
import com.capgemini.census.service.MemberInformationService;

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

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class MemberInformationController {
	@Autowired
	private MemberInformationService memberInformationService;

	//http://localhost:8081/springfox/api/member/1
	// http://localhost:8081/api/member/
	
	/**
	 * This method adds the member to the application.
	 * @param id This is the application id that is passed as the parameter to the function.
	 * @param memberInformation object of memberInformation class that contains the required information.
	 * @return Returns the memberInformation object with its status.
	 */
	
	@ApiOperation(value = "Add Member",
			consumes = "receives member object as Request body",
			response = String.class,
			httpMethod = "POST"
			)
	@PostMapping("/member/{id}")
	public ResponseEntity<MemberInformation> addMember(@PathVariable Integer id,  @RequestBody MemberInformation memberInformation) {
		try {
			MemberInformation saved  = memberInformationService.addMember(memberInformation,id);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
			
			
		} catch (MemberInformationException e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * This method returns the details of all the members in the application.
	 * @return Returns the List of all the members with the status.
	 */
	@ApiOperation(value = "Get all members",
			response = MemberInformation.class,
			tags = "get-all-members",			
			httpMethod = "GET")
	@GetMapping("/member")
	public ResponseEntity<List<MemberInformation>> getMemberAppDeatils() {
		try {
			List<MemberInformation> memberList = memberInformationService.getAllMemberDeatils();
			return new ResponseEntity<>(memberList, HttpStatus.OK);
		} catch (MemberInformationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * This method returns the member information for the specified Id.
	 * @param id This is the application id that is passed as the parameter to the function
	 * @return Returns the memberInformation object for the requested id.
	 */

	@ApiOperation(value = "Get Member by Id",
			response = MemberInformation.class,
			tags = "get-Member",
			consumes = "MemberId",
			httpMethod = "GET")
	@GetMapping("/member/{id}")
	public ResponseEntity<MemberInformation> getMemberInformationById(@PathVariable Integer id) {
		try {
			MemberInformation memberInformation = memberInformationService.getMemberInformationById(id);
			return new ResponseEntity<>(memberInformation, HttpStatus.OK);
		} catch (MemberInformationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	/**
	 * This method deletes the member Information from the record.
	 * @param id Id for which the information is to be deleted.
	 * @return Returns the memberInformation object if its present else displays the fail message.
	 */
	@ApiOperation(value = "Delete member",
			consumes = "member id",
			response = String.class,
			httpMethod = "DELETE")
	@DeleteMapping("/member/{id}")
	public String deleteMemberInformationById(@PathVariable Integer id) {
		try {
			Integer status = memberInformationService.deleteMemberInformationById(id);
			if (status == 1) {
				return "user: " + id + " deleted from database";
			} else {
				return "Unable to delete user from database";
			}

		} catch (MemberInformationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/**
	 * This method updates the member information as requested by the user.
	 * @param memberInformation The object for which the data is to be updated.
	 * @return Returns the memberInformation object with its status as updated or not.
	 */
	
	@ApiOperation(value = "Update member",
			consumes = "member object sent as request body",
			response =MemberInformation.class,
			httpMethod = "PUT")
	@PutMapping("/member")
	public ResponseEntity<MemberInformation> updateMemberInformation( @RequestBody MemberInformation memberInformation) {
		try {
			
			MemberInformation updatedMember = memberInformationService.updateMemberInformation(memberInformation);
			return new ResponseEntity<>(updatedMember, HttpStatus.OK);

		} catch (MemberInformationException e) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @param FirstName The search parameter.
	 * @return Returns the details of specified member searched by its FirstName.
	 */
	@ApiOperation(value = "Get Member by FirstName",
			response = MemberInformation.class,
			tags = "get-Member",
			consumes = "FirstName",
			httpMethod = "GET")
	@GetMapping("/member/searchbyfirst/{FirstName}")
	public ResponseEntity<List<MemberInformation>> getMemberInformationByFirstName(@PathVariable String FirstName) {
		try {
			List<MemberInformation> memberInformation = memberInformationService.getMemberInformationByFirstName(FirstName);
			return new ResponseEntity<>(memberInformation, HttpStatus.OK);
		} catch (MemberInformationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param LastName The search parameter.
	 * @return  Returns the details of specified member searched by its LastName.
	 */
	
	@ApiOperation(value = "Get Member by LastName",
			response = MemberInformation.class,
			tags = "get-Member",
			consumes = "LastName",
			httpMethod = "GET")
	@GetMapping("/member/searchbylast/{LastName}")
	public ResponseEntity<List<MemberInformation>> getMemberInformationByLastName(@PathVariable String LastName) {
		try {
			List<MemberInformation> memberInformation = memberInformationService.getMemberInformationByLastName(LastName);
			return new ResponseEntity<>(memberInformation, HttpStatus.OK);
		} catch (MemberInformationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
