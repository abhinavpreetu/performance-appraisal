package com.example.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Employee;
import com.example.views.DetailsEmpView;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
public class SecurityCtrl {
	
	@Autowired
	SecuritySer secServ;
	
	@JsonView(DetailsEmpView.ProjectDetails.class)
	@RequestMapping("/login")
	public Employee getLoggedInEmployee(){
		return secServ.getLoggedInEmployee();
	}
	
	/*@JsonView(DetailsEmpView.ProjectDetails.class)
	@RequestMapping("/user/login")
	public @ResponseBody Employee getEmployee(){
		return secServ.getLoggedInEmployee();		
	}*/
}
