/**
 * 
 */
package com.example.entities;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.views.DetailsEmpView;
import com.example.views.DetailsOfEmp;
import com.example.views.DetailsProjectView;
import com.example.views.EmpList;
import com.example.views.EmployeeListView;
import com.example.views.IsRoleAdmin;
import com.example.views.Password;
import com.example.views.ProfileView;
import com.example.views.ReviewRelationView;
import com.example.views.ManagerListView;
import com.example.views.ReviewedEmps;
import com.example.views.TriggeredProject;
import com.example.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author abhinav.preetu@darkhorseboa.com
 *
 */
@Entity
@Table
public class Employee implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	@JsonView({Views.ProjectList.class,DetailsProjectView.showDetails.class, EmployeeListView.ListOfEmp.class
		, ManagerListView.ListOfMngr.class, DetailsEmpView.ProjectDetails.class, ReviewRelationView.ReviewRelation.class,
		TriggeredProject.TriggeredProjectView.class, ReviewedEmps.ReviewedEmpsView.class, EmpList.EmpListView.class,
		IsRoleAdmin.RoleView.class, DetailsOfEmp.CompleteDetails.class, Password.ChangePassword.class})
	private Long empId;

	@Column(name = "first_name")
	@JsonView({Views.ProjectList.class,DetailsProjectView.showDetails.class, EmployeeListView.ListOfEmp.class, 
		ManagerListView.ListOfMngr.class, DetailsEmpView.ProjectDetails.class, ReviewRelationView.ReviewRelation.class,
		TriggeredProject.TriggeredProjectView.class, ReviewedEmps.ReviewedEmpsView.class, EmpList.EmpListView.class,
		DetailsOfEmp.CompleteDetails.class})
	private String fname;

	@Column(name = "last_name")
	@JsonView({Views.ProjectList.class, DetailsProjectView.showDetails.class, EmployeeListView.ListOfEmp.class, 
		ManagerListView.ListOfMngr.class, DetailsEmpView.ProjectDetails.class, ReviewRelationView.ReviewRelation.class,
		TriggeredProject.TriggeredProjectView.class, ReviewedEmps.ReviewedEmpsView.class, EmpList.EmpListView.class,
		DetailsOfEmp.CompleteDetails.class})
	private String lname;

	@JsonView({EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@Column(name = "birth_date")
	private Date birthDate;

	@JsonView({EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@Column(name = "join_date")
	private Date joinDate;

	@JsonView({EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@Column(unique=true)
	private String email;

	@JsonView({EmpList.EmpListView.class, Password.ChangePassword.class})
	private String password;

	@JsonView({EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@Column(unique=true)
	private Long contact;

	@JsonView({DetailsEmpView.ProjectDetails.class, ReviewRelationView.ReviewRelation.class, 
		TriggeredProject.TriggeredProjectView.class, EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@ManyToOne
	@JoinColumn(name = "designation", referencedColumnName = "designation")
	private Designation designation;

	@JsonView({/*DetailsEmpView.ProjectDetails.class,*/ ReviewRelationView.ReviewRelation.class, ProfileView.ProfileViewInt.class,
		EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "project_id")
	private ProjectEntity projectId;

	@JsonView({Views.ProjectList.class,DetailsProjectView.showDetails.class, EmployeeListView.ListOfEmp.class,
		ManagerListView.ListOfMngr.class, DetailsEmpView.ProjectDetails.class, EmpList.EmpListView.class, IsRoleAdmin.RoleView.class,
		 DetailsOfEmp.CompleteDetails.class})
	@OneToMany(mappedBy = "empId", fetch = FetchType.EAGER)	
	private List<Roles> role;

	public Employee() {
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public ProjectEntity getProjectId() {
		return projectId;
	}

	public void setProjectId(ProjectEntity projectId) {
		this.projectId = projectId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public List<Roles> getRole() {
		return role;
	}

	public void setRole(List<Roles> role) {
		this.role = role;
	}

	@Override
	public Collection<Roles> getAuthorities() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
