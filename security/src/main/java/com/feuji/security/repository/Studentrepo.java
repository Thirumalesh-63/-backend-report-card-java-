package com.feuji.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.security.entity.Student;
import java.util.List;


@Repository
public interface Studentrepo extends JpaRepository<Student, Integer> {
	
        Student findByEmail(String name);

        List<Student> findByName(String name);
}
