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
import com.capgemini.census.entity.User;
import com.capgemini.census.exception.MemberInformationException;
import com.capgemini.census.service.MemberInformationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class MemberInformationController {
	@Autowired
	//@Qualifier(value = "productServiceSpringData")
	private MemberInformationService memberInformationService;

	//http://localhost:8081/springfox/api/member/1
	// http://localhost:8081/api/member/
	// add member
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
			//log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	

	@ApiOperation(value = "Get Member by Id",
			response = MemberInformation.class,
			tags = "get-Member",
			consumes = "MemberId",
			httpMethod = "GET")
	@GetMapping("/member/{id}")
	public ResponseEntity<MemberInformation> getMemberInformationById(@PathVariable Integer id) {
		try {
			MemberInformation memberInformation = memberInformationService.getMemberInformationById(id);
			//log.info("Product added" + product);
			return new ResponseEntity<>(memberInformation, HttpStatus.OK);
		} catch (MemberInformationException e) {
			//log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Delete member",
			consumes = "member id",
			response = String.class,
			httpMethod = "DELETE")
	@DeleteMapping("/member/{id}")
	public String deleteMemberInformationById(@PathVariable Integer id) {
		try {
			Integer status = memberInformationService.deleteMemberInformationById(id);
			if (status == 1) {
				//log.info("user: " + id + " deleted from database");
				return "user: " + id + " deleted from database";
			} else {
				//log.debug("Unable to delete user from database");
				return "Unable to delete user from database";
			}

		} catch (MemberInformationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
	@ApiOperation(value = "Update member",
			consumes = "product object sent as request body",
			response =MemberInformation.class,
			httpMethod = "PUT")
	@PutMapping("/member/")
	public ResponseEntity<MemberInformation> updateMemberInformation(@RequestBody MemberInformation memberInformation) {
		try {
			MemberInformation updatedMember = memberInformationService.updateMemberInformation(memberInformation);
			//log.info("Product: " + product.getProductId() + " updated");
			return new ResponseEntity<>(updatedMember, HttpStatus.OK);

		} catch (MemberInformationException e) {
		//	log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
