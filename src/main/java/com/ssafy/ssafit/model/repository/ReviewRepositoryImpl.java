package com.ssafy.ssafit.model.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public class ReviewRepositoryImpl implements ReviewRepository {
	private static ReviewRepository instance = new ReviewRepositoryImpl();
	private List<Review> reviewList = new ArrayList<>();
	private int newReviewNo = 1;
	
	private ReviewRepositoryImpl() {
		LocalDate today = LocalDate.now();
	}
	
	public static ReviewRepository getInstance() {
		return instance;
	}
	
	@Override
	public List<Review> selectAll(int vno) {
		List<Review> reviews = new ArrayList<>();
	    for (Review r : reviewList) {
	        if (r.getVno() == vno) {
	        	reviews.add(r);
	        }
	    }
	    return reviews;
	}
	
	@Override
	public Review selectOne(int no) {
		for(Review review : reviewList) {
			if(review.getNo() == no) {
				return review;				
			}
		}
		return null;
	}
	
	@Override
	public void insertReview(Review review) {
		review.setNo(newReviewNo++);
		review.setCreated_at(LocalDate.now());
		review.setUpdated_at(LocalDate.now());
		reviewList.add(review);
	}
	
	@Override
	public void updateReview(Review review) {
		Review target = selectOne(review.getNo());
		if(target != null) {
			target.setTitle(review.getTitle());
			target.setContent(review.getContent());
			target.setUpdated_at(LocalDate.now());
		}
	}
	
	@Override
	public void deleteReview(int no) {
		for(int i = reviewList.size()-1; i >= 0; i--) {
			if(reviewList.get(i).getNo() == no) {
				reviewList.remove(i);
				return;
			}
		}
	}
	
	
	
}
