package com.feuji.security.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.security.entity.exam;
import com.feuji.security.service.examservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class examcontroller {
	
	@Autowired
	private examservice service;
	
	//generate a logger object from loggerfactory
	
	Logger log=LoggerFactory.getLogger(Studentcontroller.class);
	//write the  crud operations like post,get,put,patch,update,delete here and call the methods in service class
	
	//for example
	@CacheEvict(cacheNames="examlist",allEntries=true)
	@PostMapping("/exam")
	public exam createexam(@RequestBody exam exam) {
		log.info("this is an exam object in controller createexam method", exam.getTotalMarks());
		return service.addexam(exam);
	}
	@Cacheable(cacheNames="examlist")
	@GetMapping("/exam")
	public List<exam> getexam() {
		log.warn("this is warn message in getexam controoler");
		return service.getallexams();
	}
	
	
	@Cacheable(cacheNames="examById", key="#id")
	@GetMapping("/exam/{id}")
	public exam getexam(@PathVariable int id) {
		log.debug("this is debug message in getexam controoler with an id as "+id);
		return service.getexam(id);
	}
	@CacheEvict(cacheNames="examlist",allEntries=true)
	@Cacheable(cacheNames="examById", key="#exam.getExamId()")
	@PutMapping("/exam")
	public exam updateexam(@RequestBody exam exam) {
		log.error("this is error message in updateexam controoler",exam);
		return service.updateexam(exam);
	}
	
	@CacheEvict(cacheNames={"examById","examlist"}, allEntries = true, key="#examname")
	@DeleteMapping("/exam3/{examname}")
	public Map<String,String> deleteexam(@PathVariable String examname) {
		log.trace("this is trace message in deleteexam controoler with an examname as "+examname);
		service.deleteexam(examname);
		Map<String,String> m=Map.of("message","deleted");
		return m;
		
	}
	
	
	@Cacheable(cacheNames="examByexamname", key="#examname")
	@GetMapping("/exam2/{examname}")
	public exam getexambyexamname(@PathVariable String examname) {
        log.info("this is info message in getexam controoler with an examname as "+examname);
		return service.getexamByexamname(examname);
	}
	

}
