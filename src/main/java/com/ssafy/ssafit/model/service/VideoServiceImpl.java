package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.repository.VideoRepository;
import com.ssafy.ssafit.model.repository.VideoRepositoryImpl;

public class VideoServiceImpl implements VideoService {
	private static VideoService instance = new VideoServiceImpl();
	private VideoRepository videoRepository = VideoRepositoryImpl.getInstance();
	
	private VideoServiceImpl() {
		
	}
	
	public static VideoService getInstance() {
		return instance;
	}
	
	@Override
	public List<Video> getAllVideo() {
		return videoRepository.selectAll();
	}
	
	@Override
	public Video getVideo(int no) {
		return videoRepository.selectOne(no);
	}
//	
//	@Override
//	public void registerVideo(Video video) {
//		videoRepository.addVideo(video);
//	}
//	
//	@Override
//	public void editVideo(Video video) {
//		videoRepository.updateVideo(video);
//	}
//	
//	@Override
//	public void deleteVideo(int no) {
//		videoRepository.deleteVideo(no);
//	}
}
