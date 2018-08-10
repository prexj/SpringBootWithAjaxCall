package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.employee;
import com.example.repository.CrudeRepository;

@Service
public class CrudeService {

	@Autowired
	private CrudeRepository crudeRepository;
	
	
	public employee save(employee emp) {
		
		return crudeRepository.save(emp);
	}


	public employee findById(int id) {
		return crudeRepository.findOne(id);
	}


	public List<employee> findAll() {
		return crudeRepository.findAll();
	}
	

}
