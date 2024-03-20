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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.security.entity.marks;
import com.feuji.security.service.marksservice;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class markscontroller {

	@Autowired
	private marksservice service;

	Logger log=LoggerFactory.getLogger(Studentcontroller.class);


	//write the  crud operations like post,get,put,patch,update,delete here and call the methods in service class
	//for example

	@CacheEvict(cacheNames={"markslist","marksforexam","marksforsub","marksforuser"}, allEntries=true)
	@PostMapping("/marks")
	public marks create(@RequestBody marks mark) {
		log.trace("this is trace message in createmarks controoler",mark);
		return service.addMarks(mark);
	}

	@Cacheable(cacheNames="markslist")
	@GetMapping("/marks")
	public List<marks> read() {
		log.info("this is an info message in getmarks controoler");
		return service.getAllMarks();
	}

	@Cacheable(cacheNames="marksforuser", key="#userId")
	@GetMapping("/marksforuser/{userId}")
	public List<marks> getMarksbyuser(@PathVariable int userId) {
		log.warn("this is warn message in getmarks controoler with an id as "+userId);
		return service.getMarksbyuser(userId);
	}

	@Cacheable(cacheNames="marksforsub", key="#userId")
	@GetMapping("/marksforsub/{userId}")
	public List<marks> getMarksbysub(@PathVariable int userId) {
		log.warn("this is warn message in getmarks controoler with an id as "+userId);
		return service.getMarksbysub(userId);
	}
	
	@GetMapping("/marksforexamanduser/{userId}/{examName}")
	public List<marks> getMarksbyexamanduser(@PathVariable int userId,@PathVariable String examName) {
	
		return service.getMarksbyuserandexam(userId, examName);
	}

	@Cacheable(cacheNames="marksforexam", key="#examName")
	@GetMapping("/marksforexam/{examName}")
	public List<marks> getMarksbyexam(@PathVariable String examName) {
		log.warn("this is warn message in getmarks controoler with an examName as "+examName);
		return service.getMarksbyexam(examName);
	}

	@CacheEvict(cacheNames={"markslist","marksforexam","marksforsub","marksforuser"}, allEntries=true)
	@CachePut(cacheNames="marksById", key="#mark.getMId()")
	@PutMapping("/marks")
	public marks update(@RequestBody marks mark) {
		log.error("this is error message in updatemarks controoler",mark);
		return service.updateMarks(mark);
	}

	@CacheEvict(cacheNames={"markslist","marksforexam","marksforsub","marksforuser"}, allEntries=true)
	@DeleteMapping("/marksbyexam/{id}") 
	public Map<String,String> deletebyexam(@PathVariable int id){
		log.debug("this is debug message in deletemarks controoler with an id as "+id);
		service.deleteMarksbyexam(id);
		Map<String,String> m=Map.of("message","deleted");
		return m;
	}

	@CacheEvict(cacheNames={"markslist","marksforexam","marksforsub","marksforuser"}, allEntries=true)
	@DeleteMapping("/marksbysub/{id}") 
	public Map<String,String> deletebysub(@PathVariable int id){
		log.debug("this is debug message in deletemarks controoler with an id as "+id);
		service.deleteMarksbysub(id);
		Map<String,String> m=Map.of("message","deleted");
		return m;
	}
	@CacheEvict(cacheNames={"markslist","marksforexam","marksforsub","marksforuser"}, allEntries=true)
	@DeleteMapping("/marksbyuser/{id}") 
	public Map<String,String> deletemarksbyuser(@PathVariable int id){
		log.debug("this is debug message in deletemarks controoler with an id as "+id);
		service.deleteMarksbyuser(id);
		Map<String,String> m=Map.of("message","deleted");
		return m;
	}

	@CacheEvict(cacheNames={"markslist","marksforexam","marksforsub","marksforuser"}, allEntries=true)
	@DeleteMapping("/marksbyid/{id}") 
	public Map<String,String> deletemarksbyid(@PathVariable int id){
		log.debug("this is debug message in deletemarks controoler with an id as "+id);
		service.deleteMarksbyid(id);
		Map<String,String> m=Map.of("message","deleted");
		return m;
	}

}
