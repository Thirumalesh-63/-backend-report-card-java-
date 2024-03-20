package com.feuji.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.security.entity.Student;
import com.feuji.security.entity.exam;
import com.feuji.security.entity.marks;
import com.feuji.security.entity.subjects;
import com.feuji.security.repository.Studentrepo;
import com.feuji.security.repository.examrepo;
import com.feuji.security.repository.marksrepo;
import com.feuji.security.repository.subjectsrepo;

@Service
public class marksservice {

	@Autowired
	private marksrepo repo;

	@Autowired
	private subjectsrepo srepo;
	@Autowired
	private Studentrepo stdrepo;
	@Autowired
	private examrepo erepo;


	// write the crud operations for marks by using marksrepo

	public marks addMarks(marks marks) {

		subjects sub = srepo.findById(marks.getSub().getSubId()).orElse(null);
		Student std=stdrepo.findById(marks.getUser().getId()).orElse(null);
		exam exam=erepo.findById(marks.getExam().getExamId()).orElse(null);
		if (sub == null) {
			throw new RuntimeException("subject not found with this id :  " + marks.getSub().getSubId());
		}
		else if (std == null) {
			throw new RuntimeException("student not found with this id :  " + marks.getUser().getId());
		} else if (exam == null) {
			throw new RuntimeException("exam not found with this id :  " + marks.getExam().getExamId());
		}
		marks.setSub(sub);
		marks.setUser(std);
		marks.setExam(exam);
		
		List<marks> m=repo.findAll();
		for (marks m1 : m) {
			if (m1.getUser().getId() == marks.getUser().getId() && m1.getSub().getSubId() == marks.getSub().getSubId()
					&& m1.getExam().getExamId() == marks.getExam().getExamId()) {
				throw new RuntimeException("marks already added for this user with this subject and exam");
			}
		}
		
		return repo.save(marks);
	}

	public List<marks> getMarksbyuser(int id) {

		Student std=stdrepo.findById(id).orElse(null);
		if (std == null) {
			throw new RuntimeException("student not found with this id :  " + id);
		}
		return repo.findByUser(std);
	}
	
	public List<marks> getMarksbysub(int id) {

		subjects sub = srepo.findById(id).orElse(null);
		if (sub == null) {
			throw new RuntimeException(" This subject  exam is not written " + id);
		}
		return repo.findBySub(sub);
	}
	
	public List<marks> getMarksbyexam(String name) {

		exam exam=erepo.findByExamName(name);
		if (exam == null) {
			throw new RuntimeException("This exam is not found" + name);
		}
		return repo.findByExam(exam);
	}

	public void deleteMarksbyexam(int id) {
		exam exam=erepo.findById(id).orElse(null);
		if (exam == null) {
			throw new RuntimeException("exam not found with this id :  " + id);
		}
		repo.deleteByExam(exam);
	}
	
	public void deleteMarksbysub(int id) {
		subjects sub = srepo.findById(id).orElse(null);
		if (sub == null) {
			throw new RuntimeException("subject not found with this id :  " + id);
		}
		repo.deleteBySub(sub);
	}
	
	public void deleteMarksbyuser(int id) {
		Student std=stdrepo.findById(id).orElse(null);
		if (std == null) {
			throw new RuntimeException("student not found with this id :  " + id);
		}
		repo.deleteByUser(std);
	}
	
	public marks updateMarks(marks marks) {
		marks m=repo.findById(marks.getMId()).orElse(null);
		if (m==null) {
			throw new RuntimeException("marks not found with this id :  " + marks.getMId());
		}
		if (marks.getSub() != null) {
			subjects sub = srepo.findById(marks.getSub().getSubId()).orElse(null);
			if (sub == null) {
				throw new RuntimeException("subject not found with this id :  " + marks.getSub().getSubId());
			}
			m.setSub(sub);
		}
		
		if (marks.getUser() != null) {
			Student std = stdrepo.findById(marks.getUser().getId()).orElse(null);
			if (std == null) {
				throw new RuntimeException("student not found with this id :  " + marks.getUser().getId());
			}
			m.setUser(std);
		}
		if (marks.getExam() != null) {
			exam exam = erepo.findById(marks.getExam().getExamId()).orElse(null);
			if (exam == null) {
				throw new RuntimeException("exam not found with this id :  " + marks.getExam().getExamId());
			}
			m.setExam(exam);
		}
		m.setMarks(marks.getMarks());
		return repo.save(marks);
	}

	public List<marks> getAllMarks() {
		return repo.findAll();
	}
	
	
	public List<marks> getMarksbyuserandexam(int userId, String examName ) {
		Student std = stdrepo.findById(userId).orElse(null);
		exam exam = erepo.findByExamName(examName);
		if (std == null) {
			throw new RuntimeException("student not found with this id :  " + userId);
		} else if (exam == null) {
			throw new RuntimeException("exam not found with this name :  " + examName);
		}
		return repo.findByUserAndExam(std, exam);
	}

	public void deleteMarksbyid(int id) {
		// TODO Auto-generated method stub
		marks m=repo.findById(id).orElse(null);
		repo.delete(m);
		
	}
}
