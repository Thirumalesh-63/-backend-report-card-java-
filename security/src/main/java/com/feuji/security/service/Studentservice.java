package com.feuji.security.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.security.core.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feuji.security.config.jwtservice;
import com.feuji.security.entity.Role;
import com.feuji.security.entity.Student;
import com.feuji.security.entity.UserResponse;
import com.feuji.security.exceptionhandler.useralreadyexist;
import com.feuji.security.exceptionhandler.usernotfound;
import com.feuji.security.repository.Studentrepo;

@Service
public class Studentservice {

	@Autowired
	private Studentrepo repo;
	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
	private jwtservice jwtservice;
	@Autowired
	private PasswordEncoder passwordencoder;

	Logger log=LoggerFactory.getLogger(Studentservice.class);
	
	//write the crud operations for exam by using Studentrepo object
	public UserResponse createStudent(Student std) {
		Student std2=repo.findByEmail(std.getEmail());
		if(std2!=null) {
			throw new useralreadyexist("user already exist with this email_id :  " + std.getEmail());
		}
		if (std.getRole() == null) {
			std.setRole(Role.USER);
		}
		Student student=new Student();
		student.setName(std.getName());
		student.setEmail(std.getEmail());
		student.setDepartment(std.getDepartment());
		student.setPassword(passwordencoder.encode(std.getPassword()));
		student.setRole(std.getRole());
		Student s=repo.save(student);
		String jwttoken=jwtservice.geneartejwttoken(student);
		// genereate empty hashmap
		// add jwttoken to hashmap
		// add message to hashmap
		 UserResponse us=new UserResponse();
		 us.setToken(jwttoken);
		 us.setUser(s);
		return us;
	}

	public Optional<Student> getStudent(int id) {
		Optional<Student> std=repo.findById(id);
		if((!std.isPresent())) {
			throw new usernotfound("user not found with this id :  " + id);
		}
		log.info("this is an student object in service getstudent method", std);
		return std;

	}

	public void deleteStudent(int id) {
		repo.deleteById(id);

	}

	public Student updateStudent(Student std) {
		Student std2=repo.findById(std.getId()).get();


		if(std2!=null) {
			if(std.getName()!= null) {
				std2.setName(std.getName());
			}
			if(std.getRole()!= null) {
				std2.setRole(std.getRole());
			}if(std.getPassword()!= null) {
				std2.setPassword(passwordencoder.encode(std.getPassword()));
			}if(std.getEmail()!= null) {
				std2.setEmail(std.getEmail());
			}if(std.getDepartment()!= null) {
				std2.setDepartment(std.getDepartment());
			}
			
		}
		return repo.saveAndFlush(std2);
		
	}

	public List<Student> getallstudents() {
		return repo.findAll();

	}

	public UserResponse loginstudent(Student std) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						std.getEmail(),
						std.getPassword()
						)
				);
		Student user = repo.findByEmail(std.getEmail());
	
		String jwttoken = jwtservice.geneartejwttoken(user);
		 UserResponse us=new UserResponse();
		 us.setToken(jwttoken);
		 us.setUser(user);
		return us;
	}

}
