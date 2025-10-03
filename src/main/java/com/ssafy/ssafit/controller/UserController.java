package com.ssafy.ssafit.controller;

import java.io.IOException;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.model.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService = UserServiceImpl.getInstance();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String act = request.getParameter("act");
    	
    	switch(act) {
    	case "userJoinForm": 		// 회원가입 get 요청
    		doUserJoinForm(request, response);
    		break;
    	case "userJoin":			// 회원가입 post 요청
    		doUserJoin(request, response);
    		break;
    	case "userProfile":			// 회원 조회 get 요청
    		doUserProfile(request, response);
    		break;
    	case "userEditForm":		// 회원 수정 get 요청
    		doUserEditForm(request, response);
    		break;
    	case "userUpdate":			// 회원 수정 post 요청
    		doUserUpdate(request, response);
    		break;
    	case "userDeactivate":		// 회원 삭제 post 요청
    		doUserDeactivate(request, response);
    		break;
    	case "loginForm":			// 로그인 get 요청
    		doLoginForm(request, response);
    		break;	
    	case "login":		// 로그인 post 요청
    		doLogin(request, response);
    		break;
    	case "logout":		// 로그아웃 post 요청
    		doLogout(request, response);
    		break;
    	}
    }

	private void doUserJoinForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
	}

	private void doUserJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("id");
	    String pw = request.getParameter("password");
	    String confirmPw = request.getParameter("confirmPassword");
	    String email = request.getParameter("email");

	    String errorMsg = userService.register(id, pw, confirmPw, email);

	    // 회원가입 실패 시 에러 메시지 반환
	    if (errorMsg != null) {
	        request.setAttribute("errorMsg", errorMsg);
	        request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
	        return;
	    }
	    
	    response.sendRedirect("user?act=loginForm");
	}

	private void doUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		
		if (loginUser == null) {
			response.sendRedirect("user?act=loginForm");
			return;
		}
		
		request.setAttribute("user", loginUser);
		request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
	}

	private void doUserEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("user?act=loginForm");
			return;
		}
		
		request.setAttribute("user", loginUser);
		request.getRequestDispatcher("/WEB-INF/user/mypageEdit.jsp").forward(request, response);
	}

	private void doUserUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User loginUser = (User) request.getSession().getAttribute("loginUser");
	    String pw = request.getParameter("password");
	    String confirmPw = request.getParameter("confirmPassword");

	    boolean updated = userService.updatePassword(loginUser, pw, confirmPw);

	    if (updated) {
	        request.getSession().setAttribute("loginUser", loginUser);
	        response.sendRedirect("user?act=userProfile");
	    } else {
	        request.setAttribute("errorMsg", "비밀번호 변경에 실패했습니다.");
	        request.getRequestDispatcher("/WEB-INF/user/mypageEdit.jsp").forward(request, response);
	    }
	}

	private void doUserDeactivate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User loginUser = (User) request.getSession().getAttribute("loginUser");

	    if (loginUser != null && userService.deactivateUser(loginUser.getId())) {
	        request.getSession().invalidate();
	        response.sendRedirect("index.jsp");
	    } else {
	        request.setAttribute("errorMsg", "회원 탈퇴에 실패했습니다.");
	        request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
	    }
	}

	private void doLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getSession().getAttribute("loginUser") != null) {
	        response.sendRedirect("index.jsp");
	        return;
	    }

	    String id = request.getParameter("id");
	    String pw = request.getParameter("password");

	    User loginUser = userService.login(id, pw);

	    if (loginUser == null) {
	        request.setAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
	        request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
	        return;
	    }

	    request.getSession().setAttribute("loginUser", loginUser);
	    response.sendRedirect("index.jsp");
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userService.logout(request.getSession());
		response.sendRedirect("index.jsp");
	}

}
