package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Roles;

@Repository
public interface RolesRep extends JpaRepository<Roles, Long>{

	Roles findByRoleId(Long roleId);

	

}
