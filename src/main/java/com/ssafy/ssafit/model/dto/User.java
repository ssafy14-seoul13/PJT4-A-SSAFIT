package main.java.com.ssafy.ssafit.model.dto;

import java.util.List;

import java.util.List;

public class User {
	private int no;
	private String id;
	private String password;
	private String email;
	
	private List<String> savedVideoList;

	public User() {
	}
	
	public User(int no, String id, String password, String email, List<String> savedVideoList) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.email = email;
		this.savedVideoList = savedVideoList;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getSavedVideoList() {
		return savedVideoList;
	}

	public void setSavedVideoList(List<String> savedVideoList) {
		this.savedVideoList = savedVideoList;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", id=" + id + ", password=" + password + ", email=" + email + ", savedVideoList="
				+ savedVideoList + "]";
	}
	
	
	
}
