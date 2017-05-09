package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ProjectEntity;
import com.example.services.ProjectService;
import com.example.views.DetailsProjectView;
import com.example.views.TriggeredProject;
import com.example.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ProjectController {

	@Autowired
	ProjectService ser;
	
	
	@RequestMapping("/admin/addProject")
	public void addProjectagain(@RequestBody ProjectEntity project){
		//ProjectEntity p=project;
		ser.addProjectagain(project);		
		
	}
	
	
	@RequestMapping("/admin/editProject")
	public void editProject(@RequestParam("id") Long id,@RequestBody HashMap<String, Object> project){
		ser.editProject(id,project);
	}
	
	@RequestMapping("/test")
	public String tt(){
		String s="testing";
		return s;
	}
	
	@JsonView(Views.ProjectList.class)
	@RequestMapping("/admin/getProjects")
	public @ResponseBody List<ProjectEntity> getProjects(){
		return ser.getProjects();
	}
	
	@JsonView(DetailsProjectView.showDetails.class)
	@RequestMapping("/admin/showDetails")
	public @ResponseBody ProjectEntity showDetails(@RequestBody ProjectEntity project){
		return ser.showDetails(project);
	}
	
	@RequestMapping("/admin/triggerAction")
	public void triggerAction(@RequestBody ProjectEntity project){
		ser.setTriggerAction(project);
	}
	
	@JsonView(TriggeredProject.TriggeredProjectView.class)
	@RequestMapping("/admin/getTriggeredProjects")
	public @ResponseBody List<ProjectEntity> getTriggeredProjects(){
		return ser.getTriggeredProjects();
	}
}
