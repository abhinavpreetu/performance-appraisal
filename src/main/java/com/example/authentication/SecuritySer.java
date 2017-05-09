package com.example.authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.entities.Employee;


@Service
public class SecuritySer {

	public Employee getLoggedInEmployee(){
			
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() !="anonymousUser")
			{
			Employee emp = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			return emp;
			}
			else
				return null;
				
	}

	/*public Employee getEmployee() {
		// TODO Auto-generated method stub
		return (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}*/
}
