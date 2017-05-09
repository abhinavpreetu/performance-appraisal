/**
 * 
 */
package com.example.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Designation;
import com.example.entities.Employee;
import com.example.entities.Manager;
import com.example.entities.ProjectEntity;
import com.example.entities.Roles;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRep;
import com.example.repository.ProjectRepository;
import com.example.repository.RolesRep;
import com.example.views.Views.ProjectList;

/**
 * @author abhinav.preetu@darkhorseboa.com
 *
 */
@Service
public class services {
@Autowired
EmployeeRepository empR;

@Autowired
ManagerRep  managerrep; 

@Autowired
RolesRep roleRep;

@Autowired
ProjectRepository proRep;

	public void addEmp(Employee emp){
		if(empR.findByEmpId(emp.getEmpId())!= null){
			Employee employee = empR.findByEmpId(emp.getEmpId());
			employee.setBirthDate(emp.getBirthDate());
			employee.setContact(emp.getContact());
			employee.setDesignation(emp.getDesignation());
			employee.setEmail(emp.getEmail());
			employee.setFname(emp.getFname());
			employee.setJoinDate(emp.getJoinDate());
			employee.setLname(emp.getLname());
			employee.setPassword(emp.getPassword());
			//employee.setRole(emp.getRole());
			Employee editedEmp = empR.save(employee);
			if(editedEmp.getDesignation().getDesg().equalsIgnoreCase("Project Manager")){
				Manager manager= managerrep.findByMngId(emp);		
				manager.setMngId(editedEmp);
				managerrep.save(manager);
			}	
			List<Roles> roles = emp.getRole();
			for(Roles r: roles){
				Roles role = roleRep.findByRoleId(r.getRoleId());
				role.setEmpId(editedEmp);
				role.setRole(r.getRole());
				roleRep.save(role);
				if(role.getRole().equalsIgnoreCase("admin")){
					Roles roleUser = new Roles();
					roleUser.setEmpId(editedEmp);
					roleUser.setRole("user");
					roleRep.save(roleUser);
				}
			}
		}
		else{
			Employee employee = empR.save(emp);
			if(employee.getDesignation().getDesg().equalsIgnoreCase("Project Manager")){
				Manager manager=new Manager();		
				manager.setMngId(employee);
				managerrep.save(manager);
				System.out.println(manager.getMngId());
			}	
			List<Roles> roles = employee.getRole();
			for(Roles r: roles){
				r.setEmpId(employee);
				roleRep.save(r);
				if(r.getRole().equalsIgnoreCase("admin")){
					Roles roleUser = new Roles();
					roleUser.setEmpId(employee);
					roleUser.setRole("user");
					roleRep.save(roleUser);
				}
			}
		}
	}
	
	public List<Employee> getEmployees(){
		return empR.findByProjectIdIsNullAndDesignationDesgNotIn("Project Manager");
	}
	
	public List<Employee> findListOfEmployeesByProjectId(ProjectEntity project){
		return empR.findByProjectId(project);
	}
	
	public List<ProjectEntity> getProjectOfEmp(Long id) {
		// TODO Auto-generated method stub
		Employee emp = empR.findByEmpId(id);
		if(emp.getDesignation().getDesg().equalsIgnoreCase("project Manager")){
			Manager mng = managerrep.findByMngId(emp);
			List<ProjectEntity> projectList = proRep.findByManager(mng);
			return projectList;
		}
		else{
			List<ProjectEntity> projectList = new ArrayList<ProjectEntity>();
			projectList.add(emp.getProjectId());
			return projectList;
		}
	}

	public List<Employee> getEmpList() {
		return empR.findAll();
	}

	public void editProfile(Long id, HashMap<String, Object> emp) {
		Employee employee = empR.findByEmpId(id);
		/*Set<String> keysa = emp.keySet();
		//String email = emp.get('email');
		Iterator it = keysa.iterator();
		while(it.hasNext()){
			Long contact = (Long) emp.get(it.next());
		}
		Set editEmp = emp.entrySet();
		String email = editEmp.
		Iterator it = editEmp.iterator();
		while(it.hasNext()){
			HashMap.Entry mapEntry = (HashMap.Entry)it.next();
			employee.setEmail((String)mapEntry.getValue());
		}*/
		
		Long contact= Long.valueOf((String)emp.get("contact"));
		String email = (String) emp.get("email");
		System.out.println(email.getClass().getName());
		System.out.println(contact.getClass().getName());
		employee.setContact(contact);
		employee.setEmail(email);
		empR.save(employee);
	}

	public Employee getDetails(Long id) {
		// TODO Auto-generated method stub
		return empR.findByEmpId(id);
	}

	public Employee getPassword(Long id) {
		// TODO Auto-generated method stub
		Employee emp = empR.findByEmpId(id);
		return emp;
	}

	public void editPassword(Long id, HashMap<String, Object> emp) {
		// TODO Auto-generated method stub
		Employee editPassEmp = empR.findByEmpId(id);
		String newPassword = (String) emp.get("password");
		editPassEmp.setPassword(newPassword);
		empR.save(editPassEmp);
	}

	public HashMap<String, Boolean> checkForDuplicate(String email, Long contact) {
		// TODO Auto-generated method stub
		HashMap<String, Boolean> duplicateEntry = new HashMap<String, Boolean>();
		if(empR.findByEmail(email)!=null)
			duplicateEntry.put("email", true);
		else
			duplicateEntry.put("email", false);
		if(empR.findByContact(contact)!=null)
			duplicateEntry.put("contact", true);
		else
			duplicateEntry.put("contact", false);
		return duplicateEntry;
	}

	/*public Employee isRoleAdmin(Long id) {
		return empR.findByEmpId(id);
	}*/
}
