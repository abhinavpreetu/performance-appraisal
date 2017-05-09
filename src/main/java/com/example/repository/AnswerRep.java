package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Answers;
import com.example.entities.ReviewRelation;

@Repository
public interface AnswerRep extends JpaRepository<Answers, Long>{

	
	List<Answers> findByReviewRelationId(ReviewRelation id);

}
