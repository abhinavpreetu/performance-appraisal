package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.views.AnswerView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "answers")
public class Answers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private Long answerId;
	
	/*@ManyToOne
	@JoinColumn(name = "reviewing_emp", referencedColumnName = "employee_id")
	private Employee reviewingEmp;
	
	@ManyToOne
	@JoinColumn(name = "reviewed_emp", referencedColumnName = "employee_id")
	private Employee reviewedEmp;
	*/
	
	@ManyToOne
	@JoinColumn(name = "review_relation_id",referencedColumnName = "review_id")
	private ReviewRelation reviewRelationId;
	
	@JsonView(AnswerView.AnswerList.class)
	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = "question_id")
	private Questionnaire questionId;
	
	@JsonView(AnswerView.AnswerList.class)
	@Column(name = "answer")
	private String answer;
	
	public Answers(){}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	/*public Employee getReviewingEmp() {
		return reviewingEmp;
	}

	public void setReviewingEmp(Employee reviewingEmp) {
		this.reviewingEmp = reviewingEmp;
	}

	public Employee getReviewedEmp() {
		return reviewedEmp;
	}

	public void setReviewedEmp(Employee reviewedEmp) {
		this.reviewedEmp = reviewedEmp;
	}*/
	

	public Questionnaire getQuestionId() {
		return questionId;
	}

	public ReviewRelation getReviewRelationId() {
		return reviewRelationId;
	}

	public void setReviewRelationId(ReviewRelation reviewRelationId) {
		this.reviewRelationId = reviewRelationId;
	}

	public void setQuestionId(Questionnaire questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
		
}
