package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.example.views.DetailsEmpView;
import com.example.views.DetailsOfEmp;
import com.example.views.DetailsProjectView;
import com.example.views.EmpList;
import com.example.views.EmployeeListView;
import com.example.views.IsRoleAdmin;
import com.example.views.ManagerListView;
import com.example.views.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonView({Views.ProjectList.class,DetailsProjectView.showDetails.class, EmployeeListView.ListOfEmp.class
		, ManagerListView.ListOfMngr.class, DetailsEmpView.ProjectDetails.class, DetailsOfEmp.CompleteDetails.class,
		EmpList.EmpListView.class})
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Long roleId;
	
	@JsonView({Views.ProjectList.class,DetailsProjectView.showDetails.class, EmployeeListView.ListOfEmp.class
		, ManagerListView.ListOfMngr.class, DetailsEmpView.ProjectDetails.class, EmpList.EmpListView.class,
		IsRoleAdmin.RoleView.class, DetailsOfEmp.CompleteDetails.class})
	@Column(name ="role")
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "emp_id", referencedColumnName = "employee_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Employee empId;
	
	public Roles(){}
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}	
	
	
	
}
