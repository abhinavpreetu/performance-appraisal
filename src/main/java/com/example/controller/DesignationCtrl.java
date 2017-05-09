package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Designation;
import com.example.services.DesignationSer;
import com.example.views.DesignationView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class DesignationCtrl {

	@Autowired
	DesignationSer ser;
	
	@RequestMapping("/admin/addDesignation")
	public void addDesg(@RequestBody Designation desg){
		ser.addDesg(desg);
	}
	
	@JsonView(DesignationView.GetDesignation.class)
	@RequestMapping("/admin/getDesignation")
	public @ResponseBody List<Designation> getDesignation(){
		return ser.getDesignations();
	}
}
