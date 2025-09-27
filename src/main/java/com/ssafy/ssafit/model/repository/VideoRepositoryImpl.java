package com.ssafy.ssafit.model.repository;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafit.model.dto.Video;

public class VideoRepositoryImpl implements VideoRepository {

	// 싱글톤
	private static VideoRepository instance = new VideoRepositoryImpl();
	private List<Video> vidList = new ArrayList<>();
	private int nextNo = 9;
	
	private VideoRepositoryImpl() {
	}

	public static VideoRepository getInstance() {
		return instance;
	}
	
	@Override
	public List<Video> selectAll() {
		return vidList;
	}
	
	@Override
	public Video selectOne(int no) {
		for(Video video : vidList) {
			if(video.getNo() == no) {
				return video;
			}
		}
		return null;
	}
	
	@Override
	public void addVideo(Video video) {
		video.setNo(nextNo++);
		vidList.add(video);
	}
	
	@Override
	public void updateVideo(Video video) {
		Video target = selectOne(video.getNo());
		if(target != null) {
			target.setTitle(video.getTitle());
			target.setPart(video.getPart());
			target.setUrl(video.getUrl());
		}
	}
	
	@Override
	public void deleteVideo(int no) {
		for(int i = vidList.size() - 1; i >= 0; i--) {
			Video video = vidList.get(i);
			
			if(video.getNo() == no) {
				vidList.remove(i);
				return;
			}
		}
	}
	
	
}
