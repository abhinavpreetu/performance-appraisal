package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.views.ReviewRelationView;
import com.example.views.ReviewedEmps;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "review_relation", uniqueConstraints = {@UniqueConstraint(columnNames ={"reviewing_emp","reviewed_emp"})})
public class ReviewRelation {

	@JsonView({ReviewRelationView.ReviewRelation.class, ReviewedEmps.ReviewedEmpsView.class})
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "review_id")
	private Long reviewId;
		
	@ManyToOne
	@JoinColumn(name = "reviewing_emp",referencedColumnName = "employee_id")
	private Employee reviewingEmp;
	
	@JsonView({ReviewRelationView.ReviewRelation.class, ReviewedEmps.ReviewedEmpsView.class})
	@ManyToOne
	@JoinColumn(name = "reviewed_emp",referencedColumnName = "employee_id")
	private Employee reviewedEmp;
	
	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "project_id")
	private ProjectEntity projectId;
	
	@Column(name = "reviewed")
	private boolean reviewed = false;

	public ReviewRelation() {}
	
	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Employee getReviewingEmp() {
		return reviewingEmp;
	}

	public void setReviewingEmp(Employee reviewingEmp) {
		this.reviewingEmp = reviewingEmp;
	}
	

	public ProjectEntity getProjectId() {
		return projectId;
	}

	public void setProjectId(ProjectEntity projectId) {
		this.projectId = projectId;
	}

	public Employee getReviewedEmp() {
		return reviewedEmp;
	}

	public void setReviewedEmp(Employee reviewedEmp) {
		this.reviewedEmp = reviewedEmp;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}
		
}
