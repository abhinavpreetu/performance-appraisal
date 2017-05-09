package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Designation;
import com.example.entities.Questionnaire;
import com.example.entities.ReviewRelation;
import com.example.services.QuestService;
import com.example.views.QuestionnaireView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class QuestCtrl {
	
	@Autowired
	QuestService questSer;
	
	@RequestMapping("/admin/addQuestion")
	public void addQuestion1(@RequestBody Questionnaire quest){
		questSer.addQuestion1(quest);
	}
	
	@JsonView(QuestionnaireView.Questionnaire.class)
	@RequestMapping("/admin/getQuestions")
	public @ResponseBody List<Questionnaire> getQuestions(@RequestBody String desg){
		return questSer.getQuestions(desg);
	}
	
	@RequestMapping("/admin/deleteQsn")
	public void delQsn(@RequestBody Questionnaire qst){
		questSer.delQsn(qst);
	}
	
	@JsonView(QuestionnaireView.Questionnaire.class)
	@RequestMapping("/user/generateQuuestionnaire")
	public @ResponseBody List<Questionnaire> getReviewQsns(@RequestBody ReviewRelation review){
		return questSer.findQuestions(review);
	}
}
