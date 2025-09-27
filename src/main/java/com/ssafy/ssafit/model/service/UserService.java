package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	User register(String id, String password);
	User findUser(String id);
	boolean updateUser(User user);
	boolean deactivateUser(String id);
	User login(String id, String password);
	void logout(HttpSession session);
	List<User> listUser();
}
