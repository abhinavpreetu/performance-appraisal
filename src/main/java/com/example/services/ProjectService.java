package com.example.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Employee;
import com.example.entities.Manager;
import com.example.entities.ProjectEntity;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRep;
import com.example.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository rep;
	
	@Autowired
	EmployeeRepository empRep;
	
	@Autowired
	ManagerRep manrep;
	
	@Autowired
	ReviewRelationSer reviewSer;
	
	public void addProjectagain(ProjectEntity project){	
		
		rep.save(project);
		
		List<Employee> employees=project.getEmpList();
		Employee mngEmp = project.getManager().getMngId();
		Employee manager = empRep.findByEmpId(mngEmp.getEmpId());
		/*mngEmp.setProjectId(project);
		empRep.save(mngEmp);*/
		for(Employee emp:employees)
		{
			Employee employee = empRep.findByEmpId(emp.getEmpId());
			employee.setProjectId(project);
			empRep.save(employee);
		}
		employees.add(manager);
		reviewSer.populateReviewTable(employees, project);
	}
	
	public List<ProjectEntity> getProjects(){
		return rep.findAll();
	}

	public ProjectEntity showDetails(ProjectEntity project) {
		// TODO Auto-generated method stub
		return rep.findByProjectId(project.getProjectId());
	}

	public void setTriggerAction(ProjectEntity project) {
		ProjectEntity updtProject =  rep.findByProjectId(project.getProjectId());
		updtProject.setTriggeredAction(true);
		rep.saveAndFlush(updtProject);
	}

	public List<ProjectEntity> getTriggeredProjects() {
		// TODO Auto-generated method stub
		return rep.findByTriggeredAction(true);
	}

	public void editProject(Long id, HashMap<String, Object> project) {
		// TODO Auto-generated method stub
		Boolean nameFlag=false;
		Boolean descFlag=false;
		ProjectEntity editProject = rep.findByProjectId(id);
		if(!editProject.getName().equalsIgnoreCase((String)project.get("name"))){
			editProject.setName((String)project.get("name"));
			nameFlag = true;
		}
		if(!editProject.getDesc().equalsIgnoreCase((String)project.get("desc"))){
			editProject.setDesc((String)project.get("desc"));
			descFlag=true;
		}
		if(nameFlag || descFlag)
			rep.save(editProject);
	}


	
	
}
