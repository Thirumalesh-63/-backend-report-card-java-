package com.feuji.security.controller;

import java.util.List;
import java.util.Map;

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

import com.feuji.security.entity.subjects;
import com.feuji.security.service.subjectsservice;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class subjectcontroller {
	@Autowired
	private subjectsservice service;
	
	
	Logger log=LoggerFactory.getLogger(Studentcontroller.class);
	
	// write all the crud operations like post,get,put,patch,update,delete here and call the methods in service class
	
	//for example
	 @CacheEvict(cacheNames = "subjectlist", allEntries = true) 
	 @PostMapping("/subject")
		public subjects create(@RequestBody subjects sub) {
		 log.trace("this is trace message in createsubjects controoler",sub);
			return service.createSubjects(sub);
		}
	 @Cacheable(cacheNames="subjectlist")
	 @GetMapping("/subject")
		public List<subjects> read() {
			log.info("this is an info message in getsubjects controoler");
			return service.getAllSubjects();
		}

	 @Cacheable(cacheNames="subjectById", key="#id")
	 @GetMapping("/subject/{id}")
		public subjects getSubject(@PathVariable int id) {
		    log.warn("this is warn message in getsubjects controoler with an id as "+id);
			return service.getSubjects(id);
		}
	 @CacheEvict(cacheNames = "subjectlist", allEntries = true) 
	 @CachePut(cacheNames="subjectById", key="#sub.getSubId()")
	 @PutMapping("/subject")
		public subjects update(@RequestBody subjects sub) {
		   log.error("this is error message in updatesubjects controoler",sub);

			return service.updateSubjects(sub);
		}
	 @CacheEvict(cacheNames = {"subjectlist", "subjectById"}, allEntries = true, key = "#id")
	 @DeleteMapping("/subject/{id}")
		public String delete(@PathVariable int id) {
		 log.debug("this is debug message in deletesubjects controoler with an id as "+id);
			service.deleteSubjects(id);
			return "deleted";
		}
	 
	 @CacheEvict(cacheNames = "subjectlist", allEntries = true) 
	 @DeleteMapping("/subject2/{subname}")
		public Map<String,String> deletebysubname(@PathVariable String subname) {
		 log.debug("this is debug message in deletesubjects controoler with an id as "+subname);
			service.deleteSubjectsBysubname(subname);
			Map<String,String> m=Map.of("message","deleted");
			return m;
		}

}
