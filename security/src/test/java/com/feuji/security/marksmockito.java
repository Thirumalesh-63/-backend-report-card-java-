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

import com.feuji.security.controller.markscontroller;
import com.feuji.security.entity.Student;
import com.feuji.security.entity.exam;
import com.feuji.security.entity.marks;
import com.feuji.security.entity.subjects;
import com.feuji.security.repository.Studentrepo;
import com.feuji.security.repository.examrepo;
import com.feuji.security.repository.marksrepo;
import com.feuji.security.repository.subjectsrepo;
import com.feuji.security.service.marksservice;

@SpringBootTest
public class marksmockito {
	
	@InjectMocks
	private marksservice mks;
	
	@Mock
	private marksrepo mkr;
	@Mock
	private Studentrepo sr;
	@Mock
	private examrepo er;
	@Mock
	private subjectsrepo sbr;
	
	
	//generate log object by using loggerfactory
	
	Logger log=LoggerFactory.getLogger(marksmockito.class);
	// write all the predefined dummy methods for the marks service like beforeeach,aftereach,after,before
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
	
	//write the test cases for the marks service
	//for example
	@Test
	public void getMarks() {
		log.info("this is get marks");
		marks mk=new marks();
		mk.setMid(1);
		mk.setMarks(25);
		exam ex=new exam();
		ex.setExamId(1);
		ex.setExamName("annual");
		mk.setExam(ex);
		List<marks> mklist=List.of(mk);
		Optional<exam> optionalExam = Optional.of(ex); 
		when(er.findById(1)).thenReturn(optionalExam);
		when(mkr.findByExam(ex)).thenReturn(mklist);
		assertEquals(1,mks.getMarksbyexam("java").size());
		
	}
	@Test
	public void getMarks2() {
		log.info("this is get marks2");
		marks mk = new marks();
		mk.setMid(1);
		mk.setMarks(25);
		List<marks> mklist = List.of(mk);
		subjects sub=new subjects();
		sub.setSubName("urdu");
		Optional<subjects> optionalSub = Optional.of(sub);
		when(sbr.findById(1)).thenReturn(optionalSub);
		when(mkr.findBySub(sub)).thenReturn(mklist);
		assertEquals(1, mks.getMarksbysub(1).size());
	}
	@Test
	public void getMarks3() {
		log.info("this is get marks3");
		marks mk = new marks();
		mk.setMid(1);
		mk.setMarks(25);
		List<marks> mklist = List.of(mk);
		Student std=new Student();
		std.setDepartment("cse");
		Optional<Student> optionalStd = Optional.of(std);
		when(sr.findById(1)).thenReturn(optionalStd);
		when(mkr.findByUser(std)).thenReturn(mklist);
		assertEquals(1, mks.getMarksbyuser(1).size());
	}
	@Test
	public void create() {
		log.info("this is get marks4");
		marks mk = new marks();
		mk.setMid(1);
		mk.setMarks(25);
		exam ex = new exam();
		ex.setExamId(1);
		ex.setExamName("annual");
		subjects sub=new subjects();
		sub.setSubId(1);
		sub.setSubName("urdu");
		Student std=new Student();
		std.setId(1);
		std.setDepartment("cse");
		mk.setUser(std);
		mk.setSub(sub);
		mk.setExam(ex);
		Optional<exam> optionalExam = Optional.of(ex); 
		Optional<subjects> optionalSub = Optional.of(sub);
		Optional<Student> optionalStd = Optional.of(std);
		when(sr.findById(1)).thenReturn(optionalStd);
		when(sbr.findById(1)).thenReturn(optionalSub);
		when(er.findById(1)).thenReturn(optionalExam);
		when(mkr.save(mk)).thenReturn(mk);
		assertEquals("annual",mks.addMarks(mk).getExam().getExamName());
	}
	@Test
	public void update() {
		log.info("this is get marks5");
		marks mk = new marks();
		mk.setMid(1);
		mk.setMarks(25);
		exam ex = new exam();
		ex.setExamId(1);
		ex.setExamName("annual");
		subjects sub=new subjects();
		sub.setSubId(1);
		sub.setSubName("urdu");
		Student std=new Student();
		std.setId(1);
		std.setDepartment("cse");
		mk.setUser(std);
		mk.setSub(sub);
		mk.setExam(ex);
		Optional<marks> optionalmark = Optional.of(mk); 
		Optional<exam> optionalExam = Optional.of(ex); 
		Optional<subjects> optionalSub = Optional.of(sub);
		Optional<Student> optionalStd = Optional.of(std);
		when(sr.findById(1)).thenReturn(optionalStd);
		when(sbr.findById(1)).thenReturn(optionalSub);
		when(er.findById(1)).thenReturn(optionalExam);
		when(mkr.findById(1)).thenReturn(optionalmark);
		when(mkr.save(mk)).thenReturn(mk);
		assertEquals("annual", mks.updateMarks(mk).getExam().getExamName());
	}
	@Test
	public void delete() {
		log.info("this is get marks6");
		marks mk = new marks();
		mk.setMid(1);
		mk.setMarks(25);
		exam ex = new exam();
		ex.setExamId(1);
		ex.setExamName("annual");
		mk.setExam(ex);
		Optional<exam> optionalExam = Optional.of(ex); 
		when(er.findById(1)).thenReturn(optionalExam);
		mks.deleteMarksbyexam(1);;
		verify(mkr).deleteByExam(ex);
	}
	@Test
	public void delete2() {
		log.info("this is get marks7");
		marks mk = new marks();
		mk.setMid(1);
		mk.setMarks(25);
		subjects sub=new subjects();
		sub.setSubId(1);
		sub.setSubName("urdu");
		mk.setSub(sub);
		Optional<subjects> optionalSub = Optional.of(sub);
		when(sbr.findById(1)).thenReturn(optionalSub);
		mks.deleteMarksbysub(1);;
		verify(mkr).deleteBySub(sub);
	}
	@Test
	public void delete3() {
		log.info("this is get marks8");
		marks mk = new marks();
		mk.setMid(1);
		mk.setMarks(25);
		Student std=new Student();
		std.setId(1);
		std.setDepartment("cse");
		mk.setUser(std);
		Optional<Student> optionalStd = Optional.of(std);
		when(sr.findById(1)).thenReturn(optionalStd);
		mks.deleteMarksbyuser(1);
		verify(mkr).deleteByUser(std);
	}
	
	
	

}
