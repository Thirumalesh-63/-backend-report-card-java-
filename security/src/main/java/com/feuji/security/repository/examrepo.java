package com.feuji.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.security.entity.exam;

import jakarta.transaction.Transactional;

@Repository
public interface examrepo extends JpaRepository<exam, Integer>{

	exam findByExamName(String examName);
	@Transactional
	long deleteByExamName(String examName);
}
