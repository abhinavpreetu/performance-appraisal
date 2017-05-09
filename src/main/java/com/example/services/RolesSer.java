package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Roles;
import com.example.repository.RolesRep;

@Service
public class RolesSer {
	
	@Autowired
	RolesRep rolesRep;
	
}
