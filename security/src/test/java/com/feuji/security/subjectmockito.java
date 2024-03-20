package com.feuji.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.feuji.security.controller.subjectcontroller;
import com.feuji.security.entity.subjects;
import com.feuji.security.repository.subjectsrepo;
import com.feuji.security.service.subjectsservice;

@SpringBootTest
public class subjectmockito {

	// generate log object by using loggerfactory
	Logger log=LoggerFactory.getLogger(subjectmockito.class);

	@InjectMocks
	private subjectsservice subs;
	
	@Mock
	private subjectsrepo subrepo;

	// write all the predefined dummy methods for the subjects service like beforeeach,aftereach,after,before
	@BeforeClass
	public void beforeeach() {
		log.info("this is before class");
	}
	@AfterClass
	public void aftereach() {
		log.info("this is after class");
	}
	@AfterEach
	public void after() {
		log.info("this is after");
	}
	@BeforeEach
	public void before() {
		log.info("this is before");
	}

	// write the test cases for the subjects controller class
	
	// for example
	@Test
	public void getSubjects() {
		subjects sub= new subjects();
		sub.setSubName("Telugu");
		Optional<subjects> optionalsub = Optional.of(sub); 
		when(subrepo.findById(1)).thenReturn(optionalsub);
		assertEquals(sub,subs.getSubjects(1));	
	}
	@Test
	public void createsubjects() {
		subjects sub = new subjects();
		sub.setSubName("Telugu");
		when(subrepo.save(sub)).thenReturn(sub);
		assertEquals("Telugu", subs.createSubjects(sub).getSubName());
	}
	@Test
	public void updatesubjects() {
		subjects sub = new subjects();
		sub.setSubId(1);
		sub.setSubName("Hindi");
		Optional<subjects> optionalsub = Optional.of(sub);
		when(subrepo.findById(1)).thenReturn(optionalsub);
		when(subrepo.save(sub)).thenReturn(sub);
		assertEquals("Hindi", subs.updateSubjects(sub).getSubName());
	}
	@Test
	public void getallsubjects() {
		subjects sub = new subjects();
		sub.setSubName("English");
		when(subrepo.findAll()).thenReturn(List.of(sub));
		assertEquals("English", subs.getAllSubjects().get(0).getSubName());
	}
	@Test
	public void deleteSubjects() {
		subjects sub = new subjects();
		sub.setSubName("Telugu");
		Optional<subjects> optionalsub = Optional.of(sub);
		when(subrepo.findById(1)).thenReturn(optionalsub);
		subs.deleteSubjects(1);
		verify(subrepo).deleteById(1);
	}
	@Test
	public void deleteSubjectsBysubname() {
		subjects sub = new subjects();
		sub.setSubName("Telugu");
		when(subrepo.findBySubName("Telugu")).thenReturn(sub);
		sub.setSubName("Telugu");
        subs.deleteSubjectsBysubname("Telugu");
		verify(subrepo).deleteBySubName("Telugu");
	}
	
	
	


}
