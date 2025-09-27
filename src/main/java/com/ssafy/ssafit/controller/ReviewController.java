package com.ssafy.ssafit.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.ReviewService;
import com.ssafy.ssafit.model.service.ReviewServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/review")
public class ReviewController extends HttpServlet {

	private ReviewService reviewService = ReviewServiceImpl.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String act = request.getParameter("act");

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		// 로그인 검사 (리뷰 등록, 수정, 삭제는 로그인 필수)
		if (loginUser == null
				&& ("reviewCreate".equals(act) || "reviewUpdate".equals(act) || "reviewDelete".equals(act))) {
			response.sendRedirect("user?act=loginForm");
			return;
		}
		try {
			switch (act) {
			case "reviewList":
				doReviewList(request, response);
				break;
			case "reviewForm":
				doReviewForm(request, response);
				break;
			case "reviewCreate":
				doReviewCreate(request, response, loginUser);
				break;
			case "reviewEditForm":
				doReviewEditForm(request, response, loginUser);
				break;
			case "reviewUpdate":
				doReviewUpdate(request, response, loginUser);
				break;
			case "reviewDelete":
				doReviewDelete(request, response, loginUser);
				break;
			default:
				response.sendRedirect("index.jsp");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "리뷰 처리 중 오류가 발생했습니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	private void doReviewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vno = request.getParameter("vno"); // 영상 번호
        List<Review> list = reviewService.getReviewList(vno);
        request.setAttribute("reviewList", list);
        request.getRequestDispatcher("/WEB-INF/review/reviewList.jsp").forward(request, response);
    }

    /**
     * 리뷰 등록 폼 이동 (act=reviewForm)
     */
    private void doReviewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("vno", request.getParameter("vno")); 
        request.getRequestDispatcher("/WEB-INF/review/reviewForm.jsp").forward(request, response);
    }

    /**
     * 리뷰 등록 처리 (act=reviewCreate)
     */
    private void doReviewCreate(HttpServletRequest request, HttpServletResponse response, User loginUser) throws ServletException, IOException {
        String vno = request.getParameter("vno");
        String title = request.getParameter("title"); 
        String content = request.getParameter("content");
        
        // 로그인 사용자 ID를 writer로 사용
        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setWriter(String.valueOf(loginUser.getId())); 
        review.setVno(vno);
        reviewService.registerReview(review);
        
        response.sendRedirect("video?act=videoDetail&no=" + vno);
    }

    /**
     * 리뷰 수정 폼 이동 (act=reviewEditForm)
     */
    private void doReviewEditForm(HttpServletRequest request, HttpServletResponse response, User loginUser) throws ServletException, IOException {
        int reviewNo = Integer.parseInt(request.getParameter("no"));
        Review review = reviewService.getReview(reviewNo);
        
        if (review == null || !review.getWriter().equals(loginUser.getId())) {
             response.sendError(HttpServletResponse.SC_FORBIDDEN, "수정 권한이 없거나 리뷰를 찾을 수 없습니다.");
             return;
        }

        request.setAttribute("review", review);
        request.getRequestDispatcher("/WEB-INF/review/reviewEditForm.jsp").forward(request, response);
    }
    
    /**
     * 리뷰 수정 처리 (act=reviewUpdate)
     */
    private void doReviewUpdate(HttpServletRequest request, HttpServletResponse response, User loginUser) throws ServletException, IOException {
        int reviewNo = Integer.parseInt(request.getParameter("no"));
        String title = request.getParameter("title"); 
        String content = request.getParameter("content");
        
        Review existingReview = reviewService.getReview(reviewNo);
        
        if (existingReview == null || !existingReview.getWriter().equals(loginUser.getId())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "수정 권한이 없습니다.");
            return;
        }

        existingReview.setTitle(title); 
        existingReview.setContent(content);
        reviewService.editReview(existingReview);
        
        response.sendRedirect("video?act=videoDetail&no=" + existingReview.getVno());
    }

    /**
     * 리뷰 삭제 처리 (act=reviewDelete)
     */
    private void doReviewDelete(HttpServletRequest request, HttpServletResponse response, User loginUser) throws ServletException, IOException {
        int reviewNo = Integer.parseInt(request.getParameter("no"));
        
        Review existingReview = reviewService.getReview(reviewNo);

        if (existingReview == null || !existingReview.getWriter().equals(loginUser.getId())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "삭제 권한이 없거나 리뷰를 찾을 수 없습니다.");
            return;
        }
        
        String vno = existingReview.getVno();
        reviewService.removeReview(reviewNo);
        
        response.sendRedirect("video?act=videoDetail&no=" + vno);
    }
}
