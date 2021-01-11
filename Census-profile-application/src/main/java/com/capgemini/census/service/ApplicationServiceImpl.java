package com.capgemini.census.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.census.entity.Application;
import com.capgemini.census.entity.User;
import com.capgemini.census.exception.ApplicationException;
import com.capgemini.census.repository.ApplicationRepository;
import com.capgemini.census.repository.UserRepository;

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
@Service(value = "ApplicationServiceImpl")
@Transactional
public class ApplicationServiceImpl implements ApplicationService{
	
	/**
	 * This method adds the application to the database.
	 */
	@Autowired
	ApplicationRepository applicationRepositoryImpl;
	@Autowired
	UserRepository userRepository;
	public Application addApplication(Integer id,Application application) throws ApplicationException {
		
		try 
		{
		User user = userRepository.findById(id).get();
		application.setUser(user);
		return applicationRepositoryImpl.save(application);
		

		} catch(PersistenceException e) {
			throw new ApplicationException(e.getMessage(),e);
		}
		
	}
	
	/**
	 * This method returns application details that exists in the database.
	 */
	@Override
	public List<Application> getAllAppDeatils() throws ApplicationException {
		try {
			List<Application> productList=
					applicationRepositoryImpl.findAll();
			return productList;
		}catch(DataAccessException e) {
			throw new ApplicationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ApplicationException(e.getMessage(),e);
		}
	}

	/**
	 * The method returns the application details as requested by passing the Id.
	 */
	@Override
	public Application getApplicationById(Integer id) throws ApplicationException {
		try {
			Optional<Application> optional= 
					applicationRepositoryImpl.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new ApplicationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ApplicationException(e.getMessage(),e);
		}
	}
/**
 * This method deletes the Application by the specified Id.
 */
	@Override
	public Integer deleteApplicationById(Integer id) throws ApplicationException {
		try {
			applicationRepositoryImpl.deleteById(id);
			return id;
		}catch(DataAccessException e) {
			throw new ApplicationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ApplicationException(e.getMessage(),e);
		}
	}
/**
 * This method updates the information for the application.
 */
	@Override
	public Application updateApplication(Application application) throws ApplicationException {
		try {
			Application application1= 
					applicationRepositoryImpl.save(application);
			return application1;
		}catch(DataAccessException e) {
			throw new ApplicationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ApplicationException(e.getMessage(),e);
		}
	}



}
