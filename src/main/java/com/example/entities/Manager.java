package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.views.ManagerListView;
import com.example.views.TriggeredProject;
import com.example.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "manager")
public class Manager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonView(ManagerListView.ListOfMngr.class)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
	@JsonView({Views.ProjectList.class, ManagerListView.ListOfMngr.class, TriggeredProject.TriggeredProjectView.class})
	private Employee mngId;
	
	

	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getMngId() {
		return mngId;
	}

	public void setMngId(Employee mngId) {
		this.mngId = mngId;
	}

	
}
