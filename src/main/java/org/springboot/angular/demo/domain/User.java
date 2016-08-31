package org.springboot.angular.demo.domain;

import java.io.Serializable;

public class User implements Serializable {
	
	public String userId;
	public String userName;
	public String password;
	/*
	private String userId;
	private String userName;
	private String password;*/
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
