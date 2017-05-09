package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Roles;
import com.example.services.RolesSer;

@RestController
public class RolesCtrl {

	@Autowired
	RolesSer rolesSer;
}
