package com.example.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entities.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class userDetailsServiceConfig implements UserDetailsService{

	@Autowired
	EmployeeRepository empRep;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Employee loadUserByUsername(String employee) throws UsernameNotFoundException{
		Employee emp = empRep.findByEmail(employee);
		if(emp==null)
		{
			logger.info("employee not found");
			throw new UsernameNotFoundException("user doesn't exist");
		}
		return emp;
	}
	
}
