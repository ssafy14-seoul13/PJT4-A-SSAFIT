package com.ssafy.ssafit.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.ReviewService;
import com.ssafy.ssafit.model.service.ReviewServiceImpl;
import com.ssafy.ssafit.model.service.VideoService;
import com.ssafy.ssafit.model.service.VideoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/video")
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private VideoService videoService = VideoServiceImpl.getInstance();
	private ReviewService reviewService = ReviewServiceImpl.getInstance(); 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String act = req.getParameter("act");
		if (act == null) {
			act = "list";
		}

		try {
			switch (act) {
			case "list":
				doVideoList(req, resp);
				break;
			case "videoForm":
				doVideoForm(req, resp);
				break;
			case "videoCreate":
				doVideoCreate(req, resp);
				break;
			case "videoDetail":
				doVideoDetail(req, resp);
				break;
			case "videoEditForm":
				doVideoEditForm(req, resp);
				break;
			case "videoUpdate":
				doVideoUpdate(req, resp);
				break;
			case "videoDelete":
				doVideoDelete(req, resp);
				break;
			default:
				resp.sendRedirect("index.jsp");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "처리 중 오류가 발생했습니다.");
			req.getRequestDispatcher("/WEB-INF/common/error.jsp").forward(req, resp);
		}
	}

	private void doVideoCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String title = req.getParameter("title");
		String part = req.getParameter("part");
		String url = req.getParameter("url");

		Video video = new Video();
		video.setTitle(title);
		video.setPart(part);
		video.setUrl(url);

		videoService.registerVideo(video);

		resp.sendRedirect("video?act=list");

	}

	private void doVideoDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		videoService.deleteVideo(no);
		
		resp.sendRedirect("video?act=list");

	}

	private void doVideoUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		String title = req.getParameter("title");
		String part = req.getParameter("part");
		String url = req.getParameter("url");
		
		Video video = new Video(no, title, part, url);
		
		videoService.editVideo(video);
		
		resp.sendRedirect("video?act=videoDetail&no=" + no);
		

	}

	private void doVideoEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		Video video = videoService.getVideo(no);
		
		req.setAttribute("video", video);
		req.getRequestDispatcher("/WEB-INF/video/videoEdit.jsp").forward(req, resp);

	}

	private void doVideoDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			int vno = Integer.parseInt(req.getParameter("no"));
			Video video = videoService.getVideo(vno);
			
			List<Review> reviewList = reviewService.getReviewList(vno);
			
			req.setAttribute("video", video);
			req.setAttribute("reviewList", reviewList);
			req.getRequestDispatcher("/WEB-INF/video/videoDetail.jsp").forward(req, resp);

	}

	private void doVideoForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/video/videoForm.jsp").forward(req, resp);

	}

	private void doVideoList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> vidList = videoService.getAllVideo();
		req.setAttribute("videoList", vidList);
		req.getRequestDispatcher("/WEB-INF/video/videos.jsp").forward(req, resp);

	}

}
