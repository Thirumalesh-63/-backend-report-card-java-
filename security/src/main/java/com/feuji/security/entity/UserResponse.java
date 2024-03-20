package com.feuji.security.entity;

public class UserResponse {
	
    private Student user;
    private String token;

    // Getters and setters
	public Student getUser() {
		return user;
	}

	public void setUser(Student user) {
		this.user = user;
	}
		
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	// Constructor
	public UserResponse(Student user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}
	// tostring method
	@Override
	public String toString() {
		return "UserResponse [user=" + user + ", token=" + token + "]";
	}
}
