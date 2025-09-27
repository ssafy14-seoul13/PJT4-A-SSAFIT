<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFIT 메인 페이지</title>
</head>
<body>
    <h1>SSAFIT 메인 페이지</h1>

    <%-- 로그인 여부에 따라 메뉴 분기 --%>
    <c:choose>
        <%-- 로그인 된 경우 --%>
        <c:when test="${not empty sessionScope.loginUser}">
            <p>환영합니다, <strong>${sessionScope.loginUser.id}</strong>님!</p>
            <ul>
                <li><a href="user?act=userProfile">내 프로필</a></li>
                <li><a href="user?act=logout">로그아웃</a></li>
            </ul>
        </c:when>

        <%-- 로그인 안 된 경우 --%>
        <c:otherwise>
            <ul>
                <li><a href="user?act=loginForm">로그인</a></li>
                <li><a href="user?act=userJoinForm">회원가입</a></li>
            </ul>
        </c:otherwise>
    </c:choose>

    <hr>

    <%-- 공통 메뉴 --%>
    <ul>
        <li><a href="${pageContext.request.contextPath}/video?act=list">운동 영상</a></li>
        <li><a href="${pageContext.request.contextPath}/video?act=videoForm">새 영상 등록</a></li>
        <li><a href="community.jsp">커뮤니티</a></li>
    </ul>
</body>
</html>