package com.capgemini.census.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.census.entity.User;
import com.capgemini.census.exception.UserException;
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
@Service(value = "UserServiceImpl")
@Transactional
public class UserServiceImpl implements UserService{
/**
 * This method adds the user.
 */
	@Autowired
	UserRepository userDao;
	public User addUser(User user) throws UserException {
		try 
		{
			return userDao.save(user);
			
		} catch(PersistenceException e) {
			throw new UserException(e.getMessage(),e);
		}
		
	}
	/**
	 * This method returns the details of all the users in the application.
	 */
	@Override
	public List<User> getAllUserDeatils() throws UserException {
		
			try {
				List<User> productList=
						userDao.findAll();
				return productList;
			}catch(DataAccessException e) {
				throw new UserException(e.getMessage(),e);
			}catch(Exception e) {
				throw new UserException(e.getMessage(),e);
			}
		
	}
	/**
	 * This method returns the details of specified 
	 * user id.
	 */
	@Override
	public User getUserById(Integer id) throws UserException {
		try {
			Optional<User> optional= 
					userDao.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException e) {
			throw new UserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}
	/**
	 * This method deletes the details of specified 
	 * user id.
	 */
	@Override
	public Integer deleteUserById(Integer id) throws UserException {
		try {
			userDao.deleteById(id);
			return id;
		}catch(DataAccessException e) {
			throw new UserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}
	/**
	 * This method updates the details of the
	 * user.
	 */
	@Override
	public User updateUser(User user) throws UserException {
		try {
			User user1= 
					userDao.save(user);
			return user1;
		}catch(DataAccessException e) {
			throw new UserException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserException(e.getMessage(),e);
		}
	}
	

}
