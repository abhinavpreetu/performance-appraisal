/**
 * 
 */
package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Designation;
import com.example.entities.Employee;
import com.example.entities.ProjectEntity;
import com.example.entities.Vehicle;
import com.example.services.services;
import com.example.views.DetailsOfEmp;
import com.example.views.EmpList;
import com.example.views.EmployeeListView;
import com.example.views.IsRoleAdmin;
import com.example.views.Password;
import com.example.views.ProfileView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author abhinav.preetu@darkhorseboa.com
 *
 */
@RestController
public class Controller {
@Autowired
services ser;

	@RequestMapping("/user/checkForDuplicate")
	public @ResponseBody HashMap<String, Boolean> checkForDuplicate(@RequestParam("email") String email,@RequestParam("contact") Long contact){
		return ser.checkForDuplicate(email,contact);
	}
	
	@RequestMapping("/admin/addEmployee")
	public void addEmployee(@RequestBody Employee emp){
		ser.addEmp(emp);		
	}
	
	@JsonView(EmployeeListView.ListOfEmp.class)
	@RequestMapping("/admin/getEmployees")
	public @ResponseBody List<Employee> getEmployee()
	{
		return ser.getEmployees();
	}
	
	/*@RequestMapping("getData")
	public List<Vehicle> getData(){
		List<Vehicle> vehicle=new ArrayList<Vehicle>();
		
		Vehicle v=new Vehicle();
		v.setVehicleId(01);
		v.setManufactureYear("2016");
		v.setVehicleModel("1026sdf");
		v.setVehicleType("two wheeler");
		
		vehicle.add(v);
		
		return vehicle;
	}*/
	
	@JsonView(ProfileView.ProfileViewInt.class)
	@RequestMapping("/user/getProjectOfEmp")
	public List<ProjectEntity> getProjectOfEmp(@RequestParam("id") Long id){
		return ser.getProjectOfEmp(id);
	}
	
	@JsonView(EmpList.EmpListView.class)
	@RequestMapping("/admin/getEmpList")
	public @ResponseBody List<Employee> getEmpList(){
		return ser.getEmpList();
	}
	
	
	@RequestMapping("/user/editProfile")
	public void editProfile(@RequestParam("id") Long id,@RequestBody HashMap<String, Object> emp){
		ser.editProfile(id,emp);
	}
	
	@JsonView(DetailsOfEmp.CompleteDetails.class)
	@RequestMapping("/user/getDetails")
	public @ResponseBody Employee getDetails(@RequestParam("id") Long id){
		return ser.getDetails(id);
	}
	/*@JsonView(IsRoleAdmin.RoleView.class)
	@RequestMapping("/admin/isRoleAdmin") 
	public Employee isRoleAdmin(@RequestParam("id") Long id){
		return ser.isRoleAdmin(id);
	}*/
	
	@JsonView(Password.ChangePassword.class)
	@RequestMapping("/user/getPassword")
	public @ResponseBody Employee getPassword(@RequestParam("id") Long id){
		return ser.getPassword(id);
	}
	
	@RequestMapping("/user/editPassword")
	public void editPassword(@RequestParam("id") Long id, @RequestBody HashMap<String, Object> emp){
		ser.editPassword(id,emp);
	}
}
	
	
	
	
	
	
	
