package com.feuji.security.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.security.entity.Student;
import com.feuji.security.entity.UserResponse;
import com.feuji.security.service.Studentservice;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Studentcontroller {
	@Autowired
	private Studentservice service;
	//generate a logger object from loggerfactory
	Logger log=LoggerFactory.getLogger(Studentcontroller.class);
	
	//write the  crud operations like post,get,put,patch,update,delete here and call the methods in service class
	
		//for example
	@PostMapping("/createstudent")
	public UserResponse createStudent(@RequestBody Student std) {
		log.warn("===================");
		log.info("this is an student object in controller createstudent method", std);
		return service.createStudent(std);
	    
	}
	@PostMapping("/loginstudent")
	public UserResponse loginStudent(@RequestBody Student std) {
		System.out.println(std);
		return  service.loginstudent(std);
	}
	@Cacheable(cacheNames ="StudentById", key="#id")
	@GetMapping("/getstudent/{id}")
	public Optional<Student> getStudent(@PathVariable int id) {
		log.trace("this is trace message in getstudent controoler");
		log.debug("this is debug message in getstudent controoler");
		log.info("this is info message in getstudent controoler");
		log.warn("this is warn message in getstudent controoler");
		log.error("this is error message in getstudent controoler");
		
		 return  service.getStudent(id);
	    
	}
	@CachePut(cacheNames="StudentById", key="#std.getId()")
	@PutMapping("/updatestudent")
	public Student updateStudent(@RequestBody Student std) {
		System.out.println("---------------------------------");
		System.out.println(std);
		 return service.updateStudent(std);
	   
	}
	@CacheEvict(cacheNames="StudentById", key="#id")
	@DeleteMapping("/deletestudent/{id}")
	public String deleteStudent(@PathVariable int id) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	     service.deleteStudent(id);
	     return "Student deleted succesfully";
	}
	@Cacheable(cacheNames="allstudents")
	@GetMapping("/allstudents")
	public List<Student> getallstudents() {
		System.out.println("00000000000000000000000000000000000000");
		return  service.getallstudents();     
	}
	
	@GetMapping("/calculate")
	public int calculate() {
		
		return 12;
		
	}
	

}
