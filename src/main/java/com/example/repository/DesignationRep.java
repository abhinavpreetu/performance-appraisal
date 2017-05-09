package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Designation;

@Repository
public interface DesignationRep extends JpaRepository<Designation, Long> {

	Designation findByDesg(String desg);

}
