package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Employee;
import com.example.entities.Manager;
import com.example.repository.ManagerRep;

@Service
public class ManagerSer {

@Autowired
ManagerRep rep;
public void addManager(Manager mng){
	rep.save(mng);
	
}
public List<Manager> getManager() {
	return rep.findAll();
}
}
