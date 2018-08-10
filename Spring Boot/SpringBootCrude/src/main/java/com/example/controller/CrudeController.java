package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.employee;
import com.example.service.CrudeService;


@Controller
public class CrudeController {
	
	/*@RequestMapping("/")
	public String Login() {
		
		return "la_login";
	}*/
	
	@Autowired
	private CrudeService crudeService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String home(Model model) {
		System.out.println("starting page load :::: ");
		return "emp";
	}
	
	@RequestMapping(value="/showAll",method = RequestMethod.GET,produces="application/json")
	public @ResponseBody List<employee> updateEmp(Model model){
		List<employee> emplist =crudeService.findAll();
		
		//System.out.println("emp.getId() ::: "+emp.getId());
		System.out.println("showAll ::: ");
		return emplist;
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces="application/json")
	public @ResponseBody Integer save(@RequestParam("fname")String firstname,
			@RequestParam("lname")String lastname,
			@RequestParam("address")String address,
			@RequestParam("gen") String gen, Model model) {
		System.out.println(" save ::: ");
		System.out.println("firstname ::: "+firstname);
		System.out.println("lastname ::: "+lastname);
		System.out.println("address ::: "+address);
		System.out.println("gen ::: "+gen);
		employee emp = new employee();
		emp.setFirstName(firstname);
		emp.setLastName(lastname);
		emp.setAddress(address);
		emp.setGender(gen);
		emp.setDflag(1);
		String message ="";
		int i =0;
		emp=crudeService.save(emp);
		System.out.println("emp.getId() ::: "+emp.getId());
		if(emp.getId() >0) {
			message="success";
			i=1;
		}else {
			message="fail";
			i=2;
		}
		System.out.println("message ::: "+message);
		System.out.println("message ::: "+i);
		return i;
	}
	
	@RequestMapping(value ="editData",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody employee editData(@RequestParam("id") int id) {
		employee emp =crudeService.findById(id);
		return emp;
	}
	
	@RequestMapping(value="/updateEmp",method = RequestMethod.POST,produces="application/json")
	public @ResponseBody Integer updateEmp(@RequestParam("id")int id,@RequestParam("fname")String firstname,@RequestParam("lname")String lastname,
			@RequestParam("address")String address,@RequestParam("gen")String gen,Model model){
		employee emp =crudeService.findById(id);
		emp.setFirstName(firstname);
		emp.setLastName(lastname);
		emp.setAddress(address);
		emp.setDflag(emp.getDflag());
		emp.setGender(gen);
		emp=crudeService.save(emp);
		System.out.println("emp.getId() ::: "+emp.getId());
		String message ="";
		int i =0;
		if(emp.getId() >0) {
			message="success";
			i=1;
		}else {
			message="fail";
			i=2;
		}
		System.out.println("message ::: "+message);
		System.out.println("message ::: "+i);
		return i;
	}
	@RequestMapping(value="/deleteEmp",method = RequestMethod.POST, produces="application/json")
	public @ResponseBody Integer deleteEmp(@RequestParam("id")int id,Model model) {
		employee emp = crudeService.findById(id);
		emp.setFirstName(emp.getFirstName());
		emp.setLastName(emp.getLastName());
		emp.setAddress(emp.getAddress());
		emp.setDflag(0);
		emp.setGender(emp.getGender());
		emp=crudeService.save(emp);
		String message ="";
		int i =0;
		if(emp.getId() >0) {
			message="success";
			i=1;
		}else {
			message="fail";
			i=2;
		}
		System.out.println("message ::: "+message);
		System.out.println("message ::: "+i);
		return i;
		
	}
}
