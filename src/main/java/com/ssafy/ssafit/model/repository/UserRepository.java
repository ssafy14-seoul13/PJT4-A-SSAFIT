package com.ssafy.ssafit.model.repository;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;

public interface UserRepository {
    User save(User user);
    void saveUserVideos(int userNo, List<Integer> videoNos);
    User findById(String id);
    List<User> findAll();
    boolean update(User user);
    boolean delete(String id);
}
