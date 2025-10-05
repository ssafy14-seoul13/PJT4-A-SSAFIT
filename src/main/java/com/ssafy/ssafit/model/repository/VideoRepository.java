package com.ssafy.ssafit.model.repository;

import java.util.List;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoRepository {

	// 전체 영상 조회
	List<Video> selectAll();
//
//	// 개별 영상 조회
//	Video selectOne(int no);
//	
//	// 영상 추가
//	void addVideo(Video video);
//	
//	// 영상 수정
//	void updateVideo(Video video);
//
//	// 영상 삭제
//	void deleteVideo(int no);


}
