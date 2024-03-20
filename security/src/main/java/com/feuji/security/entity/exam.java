package com.feuji.security.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Exam")
public class exam {



	// write the properties like examId,examName,examDate,subId with getters and setters ,tostring

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "examId")
	private int examId;
	private String examName;
	
	private Date examDate;
	
	private int totalMarks;


	public exam(int examId, String examName, Date examDate, int totalMarks) {
		super();
		this.examId = examId;
		this.examName = examName;
		this.examDate = examDate;
		this.totalMarks = totalMarks;	
	
		
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public exam() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getExamId() {
		return examId;
	}
	
	public void setExamId(int examId) {
		this.examId = examId;
	}
	
	public String getExamName() {
		return examName;
	}
	
	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public Date getExamDate() {
		return examDate;
	}
	
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	@Override
	public String toString() {
		return "exam [examId=" + examId + ", examName=" + examName + ", examDate=" + examDate 
				+"]";
	}
	
	
	
	
	
}
