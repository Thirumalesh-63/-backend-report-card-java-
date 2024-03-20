package com.feuji.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.security.entity.Student;
import com.feuji.security.entity.exam;
import com.feuji.security.exceptionhandler.duplicateexam;
import com.feuji.security.repository.Studentrepo;
import com.feuji.security.repository.examrepo;

@Service
public class examservice {
	@Autowired
	private examrepo repo;

    @Autowired
	private Studentrepo stdrepo;
	//write the crud operations for exam by using examrepo
	
	public exam addexam(exam exam) {
		exam ex=repo.findByExamName(exam.getExamName());
		if(ex== null) {
			return repo.save(exam);
		}
		else
			 throw new duplicateexam("exam already exist with this name :  " + exam.getExamName());
	}
	
	public exam getexam(int id) {
		
		return repo.findById(id).orElse(null);
	}
    public exam getexamByexamname(String examname) {
    	exam ex=repo.findByExamName(examname);
    	        if(ex== null) {
    	        	throw new duplicateexam("exam not found with this name :  " + examname);
    	        }
		
		return ex;
	}
	
	public void deleteexam(String examname) {
		exam ex=repo.findByExamName(examname);
        if(ex== null) {
        	throw new duplicateexam("exam not found with this name :  " + examname);
        }
		repo.deleteByExamName(examname);
	}
	
	public exam updateexam(exam exam) {
		exam ex=repo.findById(exam.getExamId()).orElse(null);
        if(ex== null) {
        	throw new duplicateexam("exam not found with this id :  " + exam.getExamId());
        }
		return repo.save(exam);
	}
	
	public List<exam> getallexams() {
		return repo.findAll();
	}
	
	
}
