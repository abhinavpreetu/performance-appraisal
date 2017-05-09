package com.example.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Answers;
import com.example.entities.Questionnaire;
import com.example.entities.ReviewRelation;
import com.example.repository.AnswerRep;
import com.example.repository.QuestRepository;
import com.example.repository.ReviewRelationRep;

@Service
public class AnswerSer {

	@Autowired
	AnswerRep ansRep;
	
	@Autowired
	QuestRepository questRep;
	
	@Autowired
	ReviewRelationRep reviewRep;
	
	public void addAnswer(HashMap<Object, Object> ans,Long reviewId){
		
		Set answers=ans.entrySet();
		Iterator it1=answers.iterator();
		while(it1.hasNext())
		{
			HashMap.Entry mapEntry = (HashMap.Entry) it1.next();
			Long qsnId=Long.valueOf((String)mapEntry.getKey());
			String answer = (String)mapEntry.getValue();
			Questionnaire question = questRep.findByQuestionId(qsnId);
			ReviewRelation review = reviewRep.findByReviewId(reviewId);
			Answers answerEntity = new Answers();
			answerEntity.setAnswer(answer);
			answerEntity.setQuestionId(question);
			answerEntity.setReviewRelationId(review);
			ansRep.save(answerEntity);
			review.setReviewed(true);
			reviewRep.save(review);
		}
	}

	public List<Answers> getAnswers(Long id) {
		ReviewRelation reviewRelationId = reviewRep.findByReviewId(id);
		return ansRep.findByReviewRelationId(reviewRelationId);
	}
}
