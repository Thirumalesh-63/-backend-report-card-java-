package com.feuji.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.feuji.security.entity.Student;
import com.feuji.security.entity.exam;
import com.feuji.security.entity.marks;
import com.feuji.security.entity.subjects;

import jakarta.transaction.Transactional;
@Repository
public interface marksrepo extends JpaRepository<marks, Integer> {

	
	 List<marks> findByUser(Student user);
	
	
	public List<marks>  findByExam(exam exam);
//	
	public List<marks> findBySub(subjects sub);
	
//	
	 public List<marks> findByUserAndExam(Student student, exam exam); 

	 @Transactional
	 long deleteByUser(Student user);
	 @Transactional
	 long deleteBySub(subjects sub);
	 @Transactional
	 long deleteByExam(exam exam);
	 
	 
}
