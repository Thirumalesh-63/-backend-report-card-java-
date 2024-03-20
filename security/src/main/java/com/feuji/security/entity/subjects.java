package com.feuji.security.entity;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="subjects")
public class subjects {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// write the subId,subName properties with getters and setters, toString method 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subId")
	private int subId;
	public subjects(int subId, String subName) {
		super();
		this.subId = subId;
		this.subName = subName;
	
	}

	public subjects() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	private String subName;
	

	

	public int getSubId() {
		return subId;
	}
		
	public void setSubId(int subId) {
		this.subId = subId;
	}
		
	public String getSubName() {
		return subName;
	}
	
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	@Override
	public String toString() {
		return "subjects [subId=" + subId + ", subName=" + subName + "]";
	}
	
	

}
