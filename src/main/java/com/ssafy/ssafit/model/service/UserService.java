package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	String register(String id, String password, String confirmPw, String email);
	User login(String id, String password);
    boolean updatePassword(User user, String pw, String confirmPw);
    boolean deactivateUser(String id); 
    User findUser(String id);
    List<User> listUser();
    void logout(HttpSession session);
}