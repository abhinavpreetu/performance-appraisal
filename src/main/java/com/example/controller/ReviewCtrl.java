package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Answers;
import com.example.entities.Employee;
import com.example.entities.Manager;
import com.example.entities.ProjectEntity;
import com.example.entities.ReviewRelation;
import com.example.repository.ProjectRepository;
import com.example.repository.ReviewRelationRep;
import com.example.services.ProjectService;
import com.example.services.ReviewRelationSer;
import com.example.views.ReviewRelationView;
import com.example.views.ReviewedEmps;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ReviewCtrl {
	
	@Autowired
	ReviewRelationSer reviewSer;

	@JsonView(ReviewRelationView.ReviewRelation.class)
	@RequestMapping("/user/getMembers")
	public HashMap<String, List<ReviewRelation>> getToBeReviewedEmployees (@RequestBody Employee emp){
		return reviewSer.findToBeReviewedEmployees(emp);
	}
	
	@JsonView(ReviewedEmps.ReviewedEmpsView.class)
	@RequestMapping("/admin/getReviewedEmps")
	public @ResponseBody List<ReviewRelation> getreviewedEmps(@RequestParam("id") Long id,@RequestParam("pid") Long pid){
		return reviewSer.getreviewedEmps(id,pid);
	}
}
