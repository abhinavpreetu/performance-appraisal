package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Designation;
import com.example.entities.Questionnaire;

@Repository
public interface QuestRepository extends JpaRepository<Questionnaire, Long>{

	List<Questionnaire> findByDesg(Designation desg);

	Questionnaire findByQuestionId(Long qsnId);

}
