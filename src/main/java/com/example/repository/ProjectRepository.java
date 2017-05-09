package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Employee;
import com.example.entities.Manager;
import com.example.entities.ProjectEntity;
import com.example.entities.ReviewRelation;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>{

	ProjectEntity findByProjectId(Long projectId);

	@Query("select project from ProjectEntity project where manager = ?1")
	List<ProjectEntity> findByManager(Manager manager);

	List<ProjectEntity> findByTriggeredAction(boolean b);

}
