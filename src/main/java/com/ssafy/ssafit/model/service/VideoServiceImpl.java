package main.java.com.ssafy.ssafit.model.service;

import main.java.com.ssafy.ssafit.model.repository.VideoRepository;
import main.java.com.ssafy.ssafit.model.repository.VideoRepositoryImpl;

public class VideoServiceImpl implements VideoService {
	
	private static VideoRepository vr = VideoRepositoryImpl.getInstance();
	private static VideoServiceImpl instance = new VideoServiceImpl();

	private VideoServiceImpl() {
	}
	
	// 어플리케이션의 비스니스 로직 구현
	// repository 호출해서 데이터 조작
	// 필요에 따라 여러 repository의 기능 조합
	
	public static VideoService getInstance() {
		return instance;
	}
	
}
