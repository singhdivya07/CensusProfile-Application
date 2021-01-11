package com.capgemini.census.service;


import java.util.List;

import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.exception.MemberInformationException;


public interface MemberInformationService {
	public MemberInformation addMember(MemberInformation memInfo,Integer id) throws MemberInformationException;		
	public List<MemberInformation> getAllMemberDeatils() throws MemberInformationException;
	public MemberInformation getMemberInformationById(Integer id) throws MemberInformationException;
	public Integer deleteMemberInformationById(Integer id) throws MemberInformationException;
	public MemberInformation updateMemberInformation(MemberInformation memberInformation) throws MemberInformationException;
	public List<MemberInformation> getMemberInformationByFirstName(String firstName) throws MemberInformationException;
	public List<MemberInformation> getMemberInformationByLastName(String lastName) throws MemberInformationException;
	

}
