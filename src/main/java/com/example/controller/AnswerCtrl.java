package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Answers;
import com.example.services.AnswerSer;
import com.example.views.AnswerView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class AnswerCtrl {

	@Autowired
	AnswerSer ansSer;
	
	@RequestMapping("/user/addAnswers")
	public void addAnswer(@RequestBody HashMap<Object, Object> ans, @RequestParam("reviewId") Long reviewId){
		ansSer.addAnswer(ans,reviewId);
	}
	
	@JsonView(AnswerView.AnswerList.class)
	@RequestMapping("/admin/getAnswers")
	public @ResponseBody List<Answers> getAnswers(@RequestParam("id") Long id){
		return ansSer.getAnswers(id);
	}
}
