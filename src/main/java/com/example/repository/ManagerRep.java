package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Employee;
import com.example.entities.Manager;

@Repository
public interface ManagerRep extends JpaRepository<Manager, Long>{

	Manager findById(Long i);

	Manager findByMngId(Employee emp);

	

}
