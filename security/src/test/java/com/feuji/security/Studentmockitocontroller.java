package com.feuji.security;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feuji.security.controller.Studentcontroller;
import com.feuji.security.entity.Role;
import com.feuji.security.entity.Student;
import com.feuji.security.service.Studentservice;

// import statements...

@SpringBootTest
@AutoConfigureMockMvc
public class Studentmockitocontroller {

    @InjectMocks
    private Studentcontroller stdc;

    @Mock
    private Studentservice stds;

    
    Student std = new Student(1, "thiru", Role.ADMIN,"IT", "thiru@gmail.com", "thiru");
    Student std2 = new Student(2, "thirumal", Role.ADMIN,"IT", "thirumal@gmail.com", "thirumal");
//    @Test
//    public void createStudent() throws Exception {
//    
//    	m.put("token", "hieofffffffwbwuh");
//        // Mock the service behavior
//        when(stds.createStudent(std)).thenReturn(m);
//		assertEquals("hieofffffffwbwuh", stds.createStudent(std).get("token"));	
//    }

    @Test
    public void getStudent() throws Exception {
        // Mock the service behavior
        when(stds.getStudent(1)).thenReturn(java.util.Optional.of(std));
        assertEquals("thiru", stdc.getStudent(1).get().getName());
    }
    @Test
	public void getallstudents() throws Exception {
		// Mock the service behavior
		when(stds.getallstudents()).thenReturn(java.util.Arrays.asList(std, std2));
		assertEquals(2, stdc.getallstudents().size());
	}

	@Test
	public void updateStudent() throws Exception {
		// Mock the service behavior
		when(stds.updateStudent(std)).thenReturn(std);
		assertEquals(std, stdc.updateStudent(std));
	}

	@Test
	public void deleteStudent() throws Exception {
		stdc.deleteStudent(1);
		verify(stds).deleteStudent(1);
	}
	
}