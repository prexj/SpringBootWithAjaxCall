package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Crude;
import com.example.entity.employee;

@Transactional
public interface CrudeRepository extends JpaRepository<employee, Integer>{

	//public employee save(employee emp);

	//public employee findById(int id);

}
