package com.ssafy.ssafit.model.repository;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;

public interface UserRepository {
    void save(User user);
    User findById(String id);
    List<User> findAll();
    boolean update(User user);
    boolean delete(String id);
}
