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
	public User register(String id, String password) {
		User user = new User(id, password);
		repo.save(user);
		return user;
	}

	@Override
	public User findUser(String id) {
		return repo.findById(id);
	}

	@Override
	public boolean updateUser(User user) {
		return false;
	}

	@Override
	public boolean deactivateUser(String id) {
		return false;
	}

	@Override
	public User login(String id, String password) {
		User user = findUser(id);
		
		if (user == null) {
			return null;
		}
		if (!user.getPassword().equals(password)) {
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
