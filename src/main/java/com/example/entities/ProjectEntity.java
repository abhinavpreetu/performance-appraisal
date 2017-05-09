package com.example.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.views.DetailsEmpView;
import com.example.views.DetailsOfEmp;
import com.example.views.DetailsProjectView;
import com.example.views.EmpList;
import com.example.views.ProfileView;
import com.example.views.ReviewRelationView;
import com.example.views.TriggeredProject;
import com.example.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "Project")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "project_id")
	@JsonView({Views.ProjectList.class, /*DetailsEmpView.ProjectDetails.class,*/ ReviewRelationView.ReviewRelation.class,
		EmpList.EmpListView.class, TriggeredProject.TriggeredProjectView.class, DetailsOfEmp.CompleteDetails.class})
	private Long projectId;
	
	@JsonView({Views.ProjectList.class,/* DetailsEmpView.ProjectDetails.class,*/ ReviewRelationView.ReviewRelation.class,
		ProfileView.ProfileViewInt.class, TriggeredProject.TriggeredProjectView.class
		, EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@Column(name="name")
	private String name;
	
	@JsonView({Views.ProjectList.class, ReviewRelationView.ReviewRelation.class})
	@Column(name = "description")
	private String desc;
	
	@JsonView(DetailsProjectView.showDetails.class)
	@Column(name= "start_date")
	private Date startDate;
	
	@JsonView(DetailsProjectView.showDetails.class)
	@Column(name = "end_date")
	private Date endDate;
	
	@OneToMany(mappedBy = "projectId")
	//@JsonProperty(access = Access.WRITE_ONLY)
	@JsonView({DetailsProjectView.showDetails.class, TriggeredProject.TriggeredProjectView.class})
	private List<Employee> empList = new ArrayList<Employee>();
	
	@ManyToOne
	@JsonView({Views.ProjectList.class, TriggeredProject.TriggeredProjectView.class})
	@JoinColumn(name= "manager", referencedColumnName = "id")
	private Manager manager;

	@JsonView({/*DetailsEmpView.ProjectDetails.class,*/ ReviewRelationView.ReviewRelation.class, DetailsProjectView.showDetails.class,
		 DetailsOfEmp.CompleteDetails.class})
	@Column(name = "triggerd_action")
	private boolean triggeredAction = false;
	
  public ProjectEntity(){
	  
  }
  	
	public boolean isTriggeredAction() {
		return triggeredAction;
	}

	public void setTriggeredAction(boolean triggeredAction) {
		this.triggeredAction = triggeredAction;
	}

	public List<Employee> getEmpList() {
		return empList;
	}
	
	public void setEmpList(List<Employee> emp) {
		this.empList = emp;
	}
	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager managerId) {
		this.manager = managerId;
	}

}
