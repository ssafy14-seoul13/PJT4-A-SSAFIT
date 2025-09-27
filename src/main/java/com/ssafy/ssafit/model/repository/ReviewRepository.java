package com.ssafy.ssafit.model.repository;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewRepository {

	List<Review> selectAll(String vno);

	Review selectOne(int no);

	void insertReview(Review review);

	void deleteReview(int no);

	void updateReview(Review review);

}
