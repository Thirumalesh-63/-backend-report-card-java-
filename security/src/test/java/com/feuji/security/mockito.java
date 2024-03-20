package com.feuji.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.feuji.security.config.jwtservice;
import com.feuji.security.controller.Studentcontroller;
import com.feuji.security.entity.Role;
import com.feuji.security.entity.Student;
import com.feuji.security.repository.Studentrepo;
import com.feuji.security.service.Studentservice;

@SpringBootTest
public class mockito {


	@InjectMocks
	Studentservice stds;

	@Mock
	Studentrepo repo;

	@Mock
	private jwtservice jwtservice;

	@Mock
	 private AuthenticationManager authenticationManager;
	
	@Mock
	private PasswordEncoder passwordencoder;

	
	  
    Student std = new Student(1, "thiru", Role.ADMIN,"IT", "thiru@gmail.com", "thiru");
    Student std2 = new Student(2, "thirumal", Role.ADMIN,"IT", "thirumal@gmail.com", "thirumal");
	// write the test cases for the student
	@Test
	public void getStudent() {
		when(repo.findById(4)).thenReturn(Optional.of(std));
		assertEquals("IT",stds.getStudent(4).get().getDepartment());
	}

	@Test
	public void createStudent() {
		Student std=new Student();
		std.setName("thiru");
		std.setDepartment("IT");
		std.setEmail("thiru@gmail.com");
		std.setPassword("Thiru@123");
		std.setRole(Role.ADMIN);
		System.out.println(std.getId());
		when(repo.findByEmail("thiru@gmail.com")).thenReturn(null);
		when(passwordencoder.encode("Thiru@123")).thenReturn("Thiru@123");
		when(repo.save(std)).thenReturn(std);
		when(jwtservice.geneartejwttoken(any())).thenReturn("hbdew7rt433378dfhu3r2f67g");
		assertEquals("hbdew7rt433378dfhu3r2f67g",stds.createStudent(std));
		verify(repo).save(std);
		verify(jwtservice).geneartejwttoken(std);
	}


	@Test
	public void loginStudent() {
		// Create a mock Authentication object
		Authentication mockAuthentication = new UsernamePasswordAuthenticationToken("thiru@gmail.com", "Thiru@123");

		when(authenticationManager.authenticate(any())).thenReturn(mockAuthentication);
		 when(repo.findByEmail("thiru@gmail.com")).thenReturn(std);
		when(jwtservice.geneartejwttoken(any())).thenReturn("hbdew7rt433378dfhu3r2f67g");
		assertEquals("hbdew7rt433378dfhu3r2f67g",stds.loginstudent(std));
		  verify(authenticationManager).authenticate(any());
	        verify(repo).findByEmail("thiru@gmail.com");
	        verify(jwtservice).geneartejwttoken(std);
	        
	}


	@Test
	public void updateStudent() {
		when(repo.findById(1)).thenReturn((Optional.of(std)));
		when(repo.saveAndFlush(std)).thenReturn(std);
		assertEquals(std,stds.updateStudent(std));
	}

	@Test
	public void deleteStudent() {
		stds.deleteStudent(2);
		verify(repo, times(1)).deleteById(2);;

	}

	@Test
	public void getallstudents() {
		List<Student> li=List.of(std,std2);
		System.out.println(li);
		when(repo.findAll()).thenReturn(li);
		assertEquals("thirumal",stds.getallstudents().get(1).getName());
	}
	
	// write all the predefined dummy methods for the subjects service like beforeeach,aftereach,after,before

	@BeforeEach
	public void beforeeach() {
		System.out.println("222222222222222222222222");
	}
	@AfterEach
	public void aftereach() {
		System.out.println("333333333333333333333333");
	}
	@AfterClass
	public void afterclass() {
		System.out.println("44444444444444444444444444");
	}
	@BeforeClass
	public void beforeclass() {
		System.out.println("55555555555555555555555555");
	}
}
