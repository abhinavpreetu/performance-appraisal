package com.example.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;













import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Answers;
import com.example.entities.Employee;
import com.example.entities.Manager;
import com.example.entities.ProjectEntity;
import com.example.entities.ReviewRelation;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRep;
import com.example.repository.ProjectRepository;
import com.example.repository.ReviewRelationRep;

@Service
public class ReviewRelationSer {

	@Autowired
	ReviewRelationRep reviewRep;
	
	@Autowired
	EmployeeRepository empRep;
	
	@Autowired
	ManagerRep manRep;
	
	@Autowired
	ProjectRepository proRep;
	
	private static Logger logger = LoggerFactory.getLogger(ReviewRelationSer.class);

	
	public void populateReviewTable(List<Employee> empList, ProjectEntity project){
		List<Employee> copyOfEmpList = null;
		ReviewRelation reviewEntity = null;
		for (Employee employee : empList) {
			copyOfEmpList = new ArrayList<Employee>(empList);
			copyOfEmpList.remove(employee);
				
			for (Employee reviewed : copyOfEmpList) {
				reviewEntity = new ReviewRelation();		
				reviewEntity.setReviewingEmp(employee);
				reviewEntity.setReviewedEmp(reviewed);
				reviewEntity.setProjectId(project);
				reviewRep.save(reviewEntity);
				//logger.info("Function called");
			}
		}
	}
	
	public HashMap<String, List<ReviewRelation>> findToBeReviewedEmployees (Employee emp){
		Employee employee = empRep.findByEmpId(emp.getEmpId());
		HashMap<String, List<ReviewRelation>> map = new HashMap<String, List<ReviewRelation>>();
		if(employee.getDesignation().getDesg().equalsIgnoreCase("Project Manager")){
			//List<ReviewRelation> reviewList = new ArrayList<ReviewRelation>();
			Manager manager = new Manager();
			manager = manRep.findByMngId(employee);
			List<ProjectEntity> projectList = proRep.findByManager(manager);
			for (ProjectEntity project : projectList) {
				if(project.isTriggeredAction()){
					//reviewList.addAll(reviewRep.findByReviewingEmpAndReviewed(emp, false));
					map.put(project.getName(), reviewRep.findByReviewingEmpAndReviewedAndProject(employee, false, project));
				}
			}
			return map;
		}
		else if(employee.getProjectId().isTriggeredAction()){
			map.put(employee.getProjectId().getName(), reviewRep.findByReviewingEmpAndReviewedAndProject(employee, false, employee.getProjectId()));
			return map;
		}	
		else
			return null;
	}

	public List<ReviewRelation> getreviewedEmps(Long id,Long pid) {
		// TODO Auto-generated method stub
		Employee emp = empRep.findByEmpId(id);
		ProjectEntity project = proRep.findByProjectId(pid);
		List<ReviewRelation> reviewedEmpList = reviewRep.findByReviewingEmpAndProjectId(emp,project);
		return reviewedEmpList;
	}
}
