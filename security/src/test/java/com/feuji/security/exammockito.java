package com.feuji.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
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
import com.feuji.security.controller.examcontroller;
import com.feuji.security.entity.exam;
import com.feuji.security.repository.examrepo;
import com.feuji.security.service.examservice;

@SpringBootTest
public class exammockito {
	//generate logger object by using loggerfactory
	
	Logger log=LoggerFactory.getLogger(exammockito.class);
	
	
	@InjectMocks
	examservice exs;
	
	@Mock
	examrepo er;
	
	// write all the predefined dummy methods for the exam service like beforeeach,aftereach,after,before
	@BeforeEach
	public void beforeeach() {
		log.info("this is before each");
	}
	@AfterEach
	public void aftereach() {
		log.info("this is after each");
	}
	@AfterEach
	public void after() {
		log.info("this is after");
	}
	@BeforeEach
	public void before() {
		log.info("this is before");
	}
	
	@Test
	public void test1() {
		exam ex=new exam();
		ex.setExamId(1);
		ex.setExamName("java");
		ex.setExamDate(new Date(2023-03-03));
		// generate when(by calling exs.create()) and then return ex object 
		when(er.save(ex)).thenReturn(ex);
		//check the condition by using assertequals
		assertEquals(ex,exs.addexam(ex));
	}
	
	//generate the test methods for the exam service like test1,test2,test3,test4
	@Test
	public void test2() {
		exam ex = new exam();
		ex.setExamId(1);
		ex.setExamName("java");
		ex.setExamDate(new Date(2023 - 03 - 03));
		Optional<exam> optionalExam = Optional.of(ex); // Wrap the exam in an Optional
		when(er.findById(1)).thenReturn(optionalExam);
		assertEquals("java", exs.getexam(1).getExamName());
	}
	@Test
	public void test3() {
        exam ex = new exam();
        ex.setExamId(1);
        ex.setExamName("java");
        ex.setExamDate(new Date(2023 - 03 - 03));
        exam ex2 = new exam();
        ex2.setExamId(12);
        ex2.setExamName("ja");
        ex2.setExamDate(new Date(2023 - 03 - 03));
        List<exam> li=List.of(ex,ex2);
        when(er.findAll()).thenReturn(li);
        assertEquals("ja",exs.getallexams().get(1).getExamName());
	}
	@Test
	public void test4() {
        exam ex = new exam();
        ex.setExamId(1);
        ex.setExamName("java");
        ex.setExamDate(new Date(2023 - 03 - 03));
        when(er.findByExamName("java")).thenReturn(ex);
        assertEquals(ex,exs.getexamByexamname("java"));
	}
	@Test
	public void test5() {
		exam ex = new exam();
		ex.setExamId(1);
		ex.setExamName("java");
		ex.setExamDate(new Date(2023 - 03 - 03));
		Optional<exam> optionalExam = Optional.of(ex);
		when(er.findById(1)).thenReturn(optionalExam);
		when(exs.updateexam(ex)).thenReturn(ex);
		assertEquals(ex.getExamName(), exs.updateexam(ex).getExamName());
	}
	@Test
	public void test6() {
		exam ex = new exam();
		ex.setExamId(1);
		ex.setExamName("java");
		ex.setExamDate(new Date(2023 - 03 - 03));
		when(er.findByExamName("java")).thenReturn(ex);
		exs.deleteexam("java");
		verify(er, times(1)).deleteByExamName("java");
	}
	
        		
        		
}
