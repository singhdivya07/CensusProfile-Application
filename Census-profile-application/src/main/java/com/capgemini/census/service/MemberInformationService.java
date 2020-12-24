package com.capgemini.census.service;


import java.util.Date;
import java.util.List;

import com.capgemini.census.entity.Application;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.exception.MemberInformationException;




public interface MemberInformationService {
	public MemberInformation addMember(MemberInformation memInfo,Integer id) throws MemberInformationException;		
	 public MemberInformation searchMemberById(Integer memberId) throws MemberInformationException;
	
				//by firstName
				public List<MemberInformation> searchMemberByFirstName(String firstName) throws MemberInformationException;
				
				//by lastName
				public List<MemberInformation> searchMemberByLastName(String lastName) throws MemberInformationException;
				
				//by DOB
				public List<MemberInformation> searchMemberByDob(Date dob) throws MemberInformationException;
				public List<MemberInformation> getAllMemberDeatils() throws MemberInformationException;
				public MemberInformation getMemberInformationById(Integer id) throws MemberInformationException;
				public Integer deleteMemberInformationById(Integer id) throws MemberInformationException;
				public MemberInformation updateMemberInformation(MemberInformation memberInformation) throws MemberInformationException;

}
