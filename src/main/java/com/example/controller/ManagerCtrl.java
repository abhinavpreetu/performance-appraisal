package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Employee;
import com.example.entities.Manager;
import com.example.services.ManagerSer;
import com.example.views.ManagerListView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ManagerCtrl {
	
	@Autowired
	ManagerSer managerSer;
	
	@RequestMapping("/admin/addManager")
	public void addManager(@RequestBody Manager mng){
		managerSer.addManager(mng);
	}
	
	@JsonView(ManagerListView.ListOfMngr.class)
	@RequestMapping("/admin/getManager")
	public @ResponseBody List<Manager> getManager(){
		return managerSer.getManager();
	}
}
