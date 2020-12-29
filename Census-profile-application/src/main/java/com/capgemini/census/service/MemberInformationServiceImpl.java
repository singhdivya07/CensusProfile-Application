package com.capgemini.census.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.census.entity.Application;
import com.capgemini.census.entity.MemberInformation;
import com.capgemini.census.exception.MemberInformationException;
import com.capgemini.census.repository.ApplicationRepository;
import com.capgemini.census.repository.MemberInformationRepository;

/**
 * This is the Implementation class.
 * This class defines the application's boundary 
 * and its set of available operations from the perspective of
 *  interfacing client layers. It encapsulates the application's 
 * business logic, controlling transactions and co-ordinating 
 * responses in the implementation of its operations.
 * 
 * @author HP
 *
 */

@Service(value = "MemberInformationServiceImpl")
@Transactional
public class MemberInformationServiceImpl implements MemberInformationService {
	@Autowired
	private MemberInformationRepository memberInformationRepository ;
	@Autowired
	ApplicationRepository applicationRepositoryImpl;

	// @Override
	public MemberInformation addMember(MemberInformation memInfo,Integer id) throws MemberInformationException {
		try {

			Application application = applicationRepositoryImpl.findById(id).get();
			memInfo.setApplication(application);

			// Name validation
			String firstName = memInfo.getFirstName();
			String lastName = memInfo.getLastName();
			
			// Regex to check valid username.
			String regex = "^[A-Za-z]{2,30}[\\s'*-]*[A-Za-z]*$";

			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(firstName);
			Matcher m1 = p.matcher(lastName);

		
			if ((m.matches()) && (m1.matches())) {
				
				// Age validation
				if (memInfo.getAge() < 125) {
					
					return memberInformationRepository.save(memInfo);
					
				}

				else {
					throw new MemberInformationException("Age cannot be greater than 125 yrs");
				}
			} else
				throw new MemberInformationException("Invalid Name! Please Enter Valid Names");

		} catch (PersistenceException e) {
			throw new MemberInformationException(e.getMessage(),e);
		}

	}

	@Override
	public List<MemberInformation> getAllMemberDeatils() throws MemberInformationException {
		try {
			List<MemberInformation> memberList=
					memberInformationRepository.findAll();
			return memberList;
		}catch(DataAccessException e) {
			throw new MemberInformationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new MemberInformationException(e.getMessage(),e);
		}
	}

	@Override
	public MemberInformation getMemberInformationById(Integer id) throws MemberInformationException {
		try {
			Optional<MemberInformation> optional= 
					memberInformationRepository.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new MemberInformationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new MemberInformationException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deleteMemberInformationById(Integer id) throws MemberInformationException {
		try {
			memberInformationRepository.deleteById(id);
			return id;
		}catch(DataAccessException e) {
			throw new MemberInformationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new MemberInformationException(e.getMessage(),e);
		}
	}

	@Override
	public MemberInformation updateMemberInformation(MemberInformation memberInformation)
			throws MemberInformationException {
		try {
			MemberInformation memberInformation1= 
					memberInformationRepository.save(memberInformation);
			return memberInformation1;
		}catch(DataAccessException e) {
			throw new MemberInformationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new MemberInformationException(e.getMessage(),e);
		}
	}

	@Override
	public MemberInformation getMemberInformationByFirstName(String firstName) throws MemberInformationException {
		// TODO Auto-generated method stub
		return memberInformationRepository.findByFirstName(firstName);
	}

	@Override
	public MemberInformation getMemberInformationByLastName(String lastName) throws MemberInformationException {
		// TODO Auto-generated method stub
		return memberInformationRepository.findByLastName(lastName);
	}


}