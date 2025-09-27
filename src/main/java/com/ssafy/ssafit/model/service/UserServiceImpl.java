package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.repository.UserRepository;
import com.ssafy.ssafit.model.repository.UserRepositoryImpl;

import jakarta.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
	
	private final UserRepository repo = UserRepositoryImpl.getInstance();
	
	private static final UserService INSTANCE = new UserServiceImpl();
	private UserServiceImpl() {}
	public static UserService getInstance() {
		return INSTANCE;
	}

	@Override
	public String register(String id, String pw, String confirmPw) {
	    if (id == null || pw == null || confirmPw == null 
	        || id.trim().isEmpty() || pw.trim().isEmpty() || confirmPw.trim().isEmpty()) {
	        return "아이디와 비밀번호는 필수로 입력해야 합니다.";
	    }

	    if (findUser(id.trim()) != null) {
	        return "이미 존재하는 아이디입니다.";
	    }

	    if (!pw.equals(confirmPw)) {
	        return "비밀번호가 일치하지 않습니다.";
	    }

	    repo.save(new User(id.trim(), pw.trim()));
	    return null; // null이면 회원가입 성공
	}

	@Override
	public User findUser(String id) {
		return repo.findById(id);
	}

	@Override
	public boolean updatePassword(User user, String pw, String confirmPw) {
		// 값이 null 인 경우
	    if (user == null) {
	    	return false;
	    }
	    
	    if (pw == null || confirmPw == null || pw.trim().isEmpty() || confirmPw.trim().isEmpty()) {
	    	return false;
	    }
	    
	    // 비밀번호가 일치하지 않는 경우
	    if (!pw.equals(confirmPw)) {
	    	return false;
	    }

	    user.setPassword(pw.trim());
	    return repo.update(user);
	}

	@Override
	public boolean deactivateUser(String id) {
		return repo.delete(id);
	}

	@Override
	public User login(String id, String pw) {
	    if (id == null || pw == null || id.trim().isEmpty() || pw.trim().isEmpty()) {
	        return null;
	    }

	    User user = findUser(id.trim());
	    if (user == null || !user.getPassword().equals(pw.trim())) {
	        return null;
	    }
	    return user;
	}

	@Override
	public void logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
	}

	@Override
	public List<User> listUser() {
		return repo.findAll();
	}

}
