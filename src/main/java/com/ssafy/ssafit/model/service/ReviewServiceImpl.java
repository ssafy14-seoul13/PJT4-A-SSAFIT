package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.repository.ReviewRepository;
import com.ssafy.ssafit.model.repository.ReviewRepositoryImpl;

public class ReviewServiceImpl implements ReviewService {
	public static ReviewService instance = new ReviewServiceImpl();
	private ReviewRepository reviewRepository = ReviewRepositoryImpl.getInstance();
	
	private ReviewServiceImpl() {
		
	}
	
	public static ReviewService getInstance() {
		return instance;
	}
	
	@Override
	public List<Review> getReviewList(int vno) {
		return reviewRepository.selectAll(vno);
	}
	
	@Override
	public Review getReview(int no) {
		return reviewRepository.selectOne(no);
	}
	
	@Override
	public void registerReview(Review review) {
		reviewRepository.insertReview(review);
	}
	
	@Override
	public void editReview(Review review) {
		reviewRepository.updateReview(review);
	}
	
	@Override
	public void removeReview(int no) {
		reviewRepository.deleteReview(no);
	}
	
}
