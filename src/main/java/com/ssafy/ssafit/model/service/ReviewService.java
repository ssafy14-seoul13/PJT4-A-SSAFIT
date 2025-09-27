package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewService {

	List<Review> getReviewList(String vno);

	Review getReview(int no);

	void registerReview(Review review);

	void editReview(Review review);

	void removeReview(int no);

}
