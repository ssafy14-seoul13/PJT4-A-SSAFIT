package main.java.com.ssafy.ssafit.model.repository;

public class VideoRepositoryImpl implements VideoRepository {
	
	private static VideoRepositoryImpl instance = new VideoRepositoryImpl();
	
	private VideoRepositoryImpl() {
	}

	public static VideoRepositoryImpl getInstance() {
		return instance;
	}
	
	// 데이터 저장소(JSON 파일)에 접근해서 데이터의 CRUD 수행
	// JSON 파일 로드, 저장 로직 구현
}
