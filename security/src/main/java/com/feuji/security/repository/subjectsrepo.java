package com.feuji.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.security.entity.subjects;

import jakarta.transaction.Transactional;
@Repository
public interface subjectsrepo extends JpaRepository<subjects, Integer>{

	subjects findBySubName(String subName);
	@Transactional
	long deleteBySubName(String subName);
}
