package com.ssafy.ssafit.model.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.util.DBUtil;

public class VideoRepositoryImpl implements VideoRepository {
	
	private DBUtil util = DBUtil.getInstance();

	// 싱글톤
	private static VideoRepository instance = new VideoRepositoryImpl();
	private List<Video> vidList = new ArrayList<>();
	private int nextNo = 9;
	
	private VideoRepositoryImpl() {
		loadVideoDataFromJson();
	}

	public static VideoRepository getInstance() {
		return instance;
	}
	
	private boolean loadVideoDataFromJson() {
        String resourcePath = "data/video.json";
        
        try (BufferedReader reader = new BufferedReader(
                 new InputStreamReader(
                    // 클래스 로더를 사용하여 클래스패스에서 리소스 파일을 스트림으로 읽어옴
                    getClass().getClassLoader().getResourceAsStream(resourcePath), 
                    StandardCharsets.UTF_8))) {
            
            // 1. GSON 객체 생성
            Gson gson = new Gson();
            
            // 2. JSON 배열을 List<Video> 타입으로 변환하기 위한 Type 정의
            Type listType = new TypeToken<ArrayList<Video>>(){}.getType();
            
            // 3. JSON 파일 내용을 List<Video>로 변환 및 할당
            List<Video> loadedList = gson.fromJson(reader, listType);
            
            if (loadedList != null && !loadedList.isEmpty()) {
                vidList.addAll(loadedList);
                
                // nextNo를 초기 데이터의 최대 no + 1로 설정
                int maxNo = vidList.stream().mapToInt(Video::getNo).max().orElse(0);
                nextNo = maxNo + 1;
                System.out.println("정보: GSON을 이용한 JSON 데이터 로드 성공. 다음 등록 번호: " + nextNo);
                return true;
            }

            System.err.println("오류: video.json 파일이 비어 있거나 파싱 후 데이터가 없습니다.");
            return false;
            
        } catch (Exception e) {
            // 파일을 찾지 못하거나 (NullPointerException) JSON 파싱 오류 발생 시 (JsonSyntaxException)
            System.err.println("오류: JSON 파일 스트림 로드 또는 GSON 파싱 중 오류 발생. (" + e.getMessage() + ")");
            // e.printStackTrace(); // 상세 디버깅을 위해 활성화 가능
            return false;
        }
    }
	
	@Override
	public List<Video> selectAll() {
		String sql = "SELECT * FROM video";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Video video = new Video();
				video.setNo(rs.getInt("no"));
				video.setTitle(rs.getString("title"));
				video.setPart(rs.getString("part"));
				video.setUrl(rs.getString("url"));
				
				vidList.add(video);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(conn);
		}
		return new ArrayList<>(vidList);
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
