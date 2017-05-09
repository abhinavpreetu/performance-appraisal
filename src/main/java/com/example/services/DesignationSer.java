package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Designation;
import com.example.repository.DesignationRep;

@Service
public class DesignationSer {
	
	@Autowired
	DesignationRep rep;
	
	public void addDesg(Designation desg){

		rep.save(desg);
	}
	
	public List<Designation> getDesignations(){
		return rep.findAll();
	}
}
