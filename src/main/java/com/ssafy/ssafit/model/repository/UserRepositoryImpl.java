package com.ssafy.ssafit.model.repository;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafit.model.dto.User;

public class UserRepositoryImpl implements UserRepository {
	
	private List<User> userList = new ArrayList<>();
	
	private static final UserRepository INSTANCE = new UserRepositoryImpl();
	private UserRepositoryImpl() {}
	public static UserRepository getInstance() {
		return INSTANCE;
	}
	
	
	@Override
	public void save(User user) {
		userList.add(user);
	}
	
	@Override
	public User findById(String id) {
		for (User user : userList) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public List<User> findAll() {
		return new ArrayList<>(userList);
	}
	
	@Override
	public boolean update(User updateUser) {
		User foundUser = findById(updateUser.getId());
		
		if (foundUser != null) {
			foundUser.setPassword(updateUser.getPassword());
	        foundUser.setSavedVideoList(updateUser.getSavedVideoList());
			return true;
		}

		return false;
	}
	
	@Override
	public boolean delete(String id) {
		User foundUser = findById(id);
		
		if (foundUser != null) {
			userList.remove(foundUser);
			return true;
		}
		return false;
	}

}
