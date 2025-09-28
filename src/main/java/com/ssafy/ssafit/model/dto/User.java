package com.ssafy.ssafit.model.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
	private static int sequence = 1;
	
	private int no;
	private String id;
	private String password;	
	private List<String> savedVideoList;

	public User() {
	}
	
	public User(String id, String password) {
		this.no = sequence++;
		this.id = id;
		this.password = password;
		this.savedVideoList = new ArrayList<>();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getSavedVideoList() {
		return savedVideoList;
	}

	public void setSavedVideoList(List<String> savedVideoList) {
		this.savedVideoList = savedVideoList;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", id=" + id + ", password=" + password + ", savedVideoList=" + savedVideoList + "]";
	}

}
