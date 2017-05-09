package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.views.DesignationView;
import com.example.views.DetailsEmpView;
import com.example.views.DetailsOfEmp;
import com.example.views.EmpList;
import com.example.views.QuestionnaireView;
import com.example.views.ReviewRelationView;
import com.example.views.TriggeredProject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "designation")
public class Designation {

	@JsonView({DetailsEmpView.ProjectDetails.class, DesignationView.GetDesignation.class, ReviewRelationView.ReviewRelation.class
		,TriggeredProject.TriggeredProjectView.class, EmpList.EmpListView.class, DetailsOfEmp.CompleteDetails.class})
	@Id
	@Column(name = "designation",unique=true)
	private String desg;
	
	@OneToMany(mappedBy = "desg")
	/*@JsonProperty(access = Access.WRITE_ONLY)*/
	private List<Questionnaire> questList = new ArrayList<Questionnaire>();
	
	public Designation() {
		super();
	}

	public List<Questionnaire> getQuestList() {
		return questList;
	}

	public void setQuestList(List<Questionnaire> questList) {
		this.questList = questList;
	}

	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}
	

	
}
