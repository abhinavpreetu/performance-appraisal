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
import com.example.views.QuestionnaireView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {
	
	@JsonView(QuestionnaireView.Questionnaire.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Long questionId;
	
	@JsonView({QuestionnaireView.Questionnaire.class, AnswerView.AnswerList.class})
	@Column(name = "question")
	private String question;
	
	@ManyToOne
	@JoinColumn(name = "designation", referencedColumnName = "designation")
	private Designation desg;
	
	@JsonView(QuestionnaireView.Questionnaire.class)
	@Column(name = "yes_no")
	private boolean yesOrNo = false;
	
	protected Questionnaire(){}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	
	public boolean isYesOrNo() {
		return yesOrNo;
	}

	public void setYesOrNo(boolean yesOrNo) {
		this.yesOrNo = yesOrNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Designation getDesg() {
		return desg;
	}

	public void setDesg(Designation desg) {
		this.desg = desg;
	}
	
	
	
}
