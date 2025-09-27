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
		
		// 값이 null 인 경우
		if (id == null || pw == null || confirmPw == null || id.trim().isEmpty() || pw.trim().isEmpty() || confirmPw.trim().isEmpty()) {
			request.setAttribute("errorMsg", "아이디와 비밀번호는 필수로 입력해야 합니다.");
			request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
			return;
		}
		
		// 이미 존재하는 회원인 경우
		if (userService.findUser(id) != null) {
			request.setAttribute("errorMsg", "이미 존재하는 아이디입니다.");
			request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
			return;
		}
		
		// 비밀번호가 일치하지 않는 경우
		if (!pw.equals(confirmPw)) {
			request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
			request.setAttribute("id", id);
			request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
			return;
		}
		
		// 회원 등록 후 로그인 페이지로 redirect (msg, "회원가입이 완료되었습니다. 로그인을 진행해주세요.")
		userService.register(id, pw);
		response.sendRedirect("user?act=loginForm"); 
	}

	private void doUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void doUserEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void doUserUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void doUserDeactivate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void doLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이미 로그인이 되어 있는 경우
		if (request.getSession().getAttribute("loginUser") != null) {
			response.sendRedirect("index.jsp");
			return;
		}
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		// 값이 null 인 경우
		if (id == null || pw == null || id.trim().isEmpty() || pw.trim().isEmpty()) {
			request.setAttribute("errorMsg", "아이디와 비밀번호는 필수로 입력해야 합니다.");
			request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
			return;
		}
		
		User loginUser = userService.login(id, pw);
		
		// 로그인 성공 시 User 담김, 실패 시 null 반환
		if (loginUser == null) {
			request.setAttribute("errorMsg", "일치하는 회원의 정보가 없습니다. 아이디 또는 비밀번호를 다시 확인하세요.");
			request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
			return;
		}
		
		// 로그인 성공 시 세션 저장 후 main 페이지로 redirect
		request.getSession().setAttribute("loginUser", loginUser);
		response.sendRedirect("index.jsp");
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userService.logout(request.getSession());
		response.sendRedirect("index.jsp");
	}

}
