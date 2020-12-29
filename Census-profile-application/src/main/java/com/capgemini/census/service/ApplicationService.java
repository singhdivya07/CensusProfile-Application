package com.capgemini.census.service;

import java.util.List;

import com.capgemini.census.entity.Application;
import com.capgemini.census.exception.ApplicationException;

public interface ApplicationService {
	public Application addApplication(Integer id,Application application) throws ApplicationException;
	public List<Application> getAllAppDeatils() throws ApplicationException;
	public Application getApplicationById(Integer id) throws ApplicationException;
	public Integer deleteApplicationById(Integer id) throws ApplicationException;
	public Application updateApplication(Application application) throws ApplicationException;
}
