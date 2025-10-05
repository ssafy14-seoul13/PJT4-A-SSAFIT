package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoService {

	// 전체 영상 조회
	List<Video> getAllVideo();

	// 개별 영상 조회
	Video getVideo(int no);

	// 영상 추가
	void registerVideo(Video video);

	// 영상 수정
	void editVideo(Video video);

	// 영상 삭제
	void deleteVideo(int no);

}
