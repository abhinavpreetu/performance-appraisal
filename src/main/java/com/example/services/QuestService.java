package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Designation;
import com.example.entities.Employee;
import com.example.entities.Questionnaire;
import com.example.entities.ReviewRelation;
import com.example.repository.DesignationRep;
import com.example.repository.EmployeeRepository;
import com.example.repository.QuestRepository;
import com.example.repository.ReviewRelationRep;

@Service
public class QuestService {

	@Autowired
	QuestRepository questRep;
	
	@Autowired
	EmployeeRepository empRep;
	
	@Autowired
	DesignationRep desgRep;
	
	@Autowired
	ReviewRelationRep revRep;
	
	public void addQuestion1(Questionnaire quest){
		questRep.save(quest);
	}
	
	public List<Questionnaire> getQuestions(String desg){
		Designation designation = desgRep.findByDesg(desg);
		return questRep.findByDesg(designation);
	}
	
	public void delQsn(Questionnaire qst){
		questRep.delete(qst);
	}

	public List<Questionnaire> findQuestions(ReviewRelation review) {
		// TODO Auto-generated method stub
		Employee reviewedEmployee = empRep.findByEmpId(review.getReviewedEmp().getEmpId());
		Designation designation = reviewedEmployee.getDesignation();
		return questRep.findByDesg(designation);
	}
}
