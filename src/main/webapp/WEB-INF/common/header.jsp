<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <h2><a href="${pageContext.request.contextPath}/index.jsp">SSAFIT</a></h2>
    <nav>
        <a href="${pageContext.request.contextPath}/video?act=list">영상목록</a>

        <c:choose>
            <c:when test="${empty sessionScope.loginUser}">
                <a href="${pageContext.request.contextPath}/user?act=loginForm">로그인</a>
                <a href="${pageContext.request.contextPath}/user?act=userJoinForm">회원가입</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/user?act=userProfile">마이페이지</a>
                <a href="${pageContext.request.contextPath}/user?act=logout">로그아웃</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>
