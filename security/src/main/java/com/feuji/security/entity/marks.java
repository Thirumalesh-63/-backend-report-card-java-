package com.feuji.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Marks")
public class marks {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// write the properties like mid,userId,subId,marks ,examId with getters and setters, toString method

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mid;

	private int marks;

	
	public marks(int mid, int userId, int subId, int marks, int examId, Student user, subjects sub,
			com.feuji.security.entity.exam exam) {
		super();
		this.mid = mid;

		this.marks = marks;

	}
	 @ManyToOne
	    @JoinColumn(name = "user_id")
	    private Student user;

	    public Student getUser() {
		return user;
	}


	public void setUser(Student user) {
		this.user = user;
	}


	public subjects getSub() {
		return sub;
	}

	public void setSub(subjects sub) {
		this.sub = sub;
	}


	public exam getExam() {
		return exam;
	}


	public void setExam(exam exam) {
		this.exam = exam;
	}
		@ManyToOne
	    @JoinColumn(name = "sub_id")
	    private subjects sub;

	    @ManyToOne
	    @JoinColumn(name = "exam_id")
	    private exam exam;

	public marks() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public int getMId() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
	
	public int getMarks() {
		return marks;
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "marks [mid=" + mid + ", marks=" + marks + ", user=" + user + ", sub=" + sub + ", exam=" + exam + "]";
	}
	
	
	
	
}
