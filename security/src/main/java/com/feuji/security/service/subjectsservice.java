package com.feuji.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.security.entity.subjects;
import com.feuji.security.repository.subjectsrepo;

@Service
public class subjectsservice {
	@Autowired
	private subjectsrepo repo;
	
	// write the crud operations of subjects by using subject6repo
	public subjects createSubjects(subjects s) {
		subjects sub=repo.findBySubName(s.getSubName());
		if (sub == null) {
			return repo.save(s);
		} else
			throw new RuntimeException("subject already exist with this name :  " + s.getSubName());
		
	}
	
	public subjects updateSubjects(subjects s) {
		subjects sub=repo.findById(s.getSubId()).orElse(null);
		if (sub == null) {
			throw new RuntimeException("subject not found with this id :  " + s.getSubId());
		}
		return repo.save(s);
	}
	
	public void deleteSubjectsBysubname(String subname) {
		subjects sub=repo.findBySubName(subname);
		if (sub == null) {
			throw new RuntimeException("subject not found with this id :  " + subname);
		}
		repo.deleteBySubName(subname);
	}
	
	public void deleteSubjects(int id) {
		subjects sub=repo.findById(id).orElse(null);
		if (sub == null) {
			throw new RuntimeException("subject not found with this id :  " + id);
		}
		repo.deleteById(id);
	}
	
	public subjects getSubjects(int id) {
		subjects sub=repo.findById(id).orElse(null);
		if (sub == null) {
			throw new RuntimeException("subject not found with this id :  " + id);
		}
		return repo.findById(id).get();
	}
	
	public List<subjects> getAllSubjects() {
		return repo.findAll();
	}
	
	
	
	
	
	
}
