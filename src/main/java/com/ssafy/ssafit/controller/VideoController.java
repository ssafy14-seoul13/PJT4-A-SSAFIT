package main.java.com.ssafy.ssafit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.com.ssafy.ssafit.model.service.VideoService;
import main.java.com.ssafy.ssafit.model.service.VideoServiceImpl;

@WebServlet("/video")
public class VideoController extends HttpServlet {

	// 클라이언트의 요청 받아서 service 계층으로 전달
	// service의 처리 결과 받아 적절한 view(jsp) 선택해서 응답
	// 비즈니스 로직 처리 X
	
	private static final long serialVersionUID = 1L;
	VideoService videoService = VideoServiceImpl.getInstance();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
	}
	
	// 영상 등록
	// 영상 조회
	// 영상 수정
	// 영상 찜
	// 찜 영상 삭제
}
