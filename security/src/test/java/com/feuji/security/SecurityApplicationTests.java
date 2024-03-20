package com.feuji.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.feuji.security.controller.Studentcontroller;
import com.feuji.security.entity.Student;
import com.feuji.security.service.Studentservice;
import java.util.List;
@SpringBootTest

class SecurityApplicationTests {
	
	
@Autowired
Studentcontroller stdc;
	
	@Test
	public void calc() {
		assertEquals(12, new Studentcontroller().calculate());
	}
	@Test
	public void getstudent() {
	
	    Student std=stdc.getStudent(2).get();
	 
		assertEquals(2,std.getId());
	}
	@Test
	public void getallstudents() {

	    List<Student> std=stdc.getallstudents();
	   
		assertEquals(3,std.size());
	}
	
	// write all the predefined dummy methods for the subjects service like beforeeach,aftereach,after,before

    @BeforeEach
    public void beforeeach() {
    	System.out.println("+++++++++++++++++++++");
    }
    @AfterEach
    public void aftereach() {
    	System.out.println("----------------------");
    }
    @AfterClass
    public void afterclass() {
    	System.out.println("**********************");
    }
    @BeforeClass
    public void beforeclass() {
    	System.out.println("=========================");
    }
}
