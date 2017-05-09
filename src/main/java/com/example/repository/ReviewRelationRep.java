package com.example.repository;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Employee;
import com.example.entities.ProjectEntity;
import com.example.entities.ReviewRelation;

@Repository
public interface ReviewRelationRep extends JpaRepository<ReviewRelation, Long>{

	@Query("select reviewedEmp from ReviewRelation reviewedEmp where reviewingEmp = ?1 AND reviewed = ?2 and projectId = ?3")
	List<ReviewRelation> findByReviewingEmpAndReviewedAndProject(Employee emp,boolean b, ProjectEntity project);

	ReviewRelation findByReviewId(Long reviewId);

	List<ReviewRelation> findByReviewingEmp(Employee emp);

	List<ReviewRelation> findByReviewingEmpAndProjectId(Employee emp,
			ProjectEntity project);
}