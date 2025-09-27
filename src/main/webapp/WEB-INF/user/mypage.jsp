<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<h1>마이페이지</h1>
	
	<%-- 로그인 하지 않은 경우, 접근 불가능 --%>
	<c:if test="${empty sessionScope.loginUser}">
		<p style="color:red;">로그인 후 이용 가능합니다.</p>
		<a href="user?act=loginForm">로그인 페이지로 이동</a>
	</c:if>
	
	<%-- 로그인 한 경우 --%>
	<c:if test="${not empty sessionScope.loginUser}">
		<c:if test="${not empty errorMsg}">
            <p style="color:red;">${errorMsg}</p>
        </c:if>
        
		<h3>내 정보</h3>
		<p>아이디: <strong>${sessionScope.loginUser.id}</strong></p>
		
		<h3>저장한 영상 목록</h3>
		<c:choose>
			<c:when test="${empty sessionScope.loginUser.savedVideoList}">
				<p>저장한 영상이 없습니다.</p>
			</c:when>
			<c:otherwise>
				<ul>
					<c:forEach var="video" items="${sessionScope.loginUser.savedVideoList}">
						<li>${video}</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</c:if>
	
	<hr>
	
	<%-- 회원정보 수정 --%>
	<h3>내 정보</h3>
	<form action="user" method="GET">
		<input type="hidden" name="act" value="userEditForm">
		<input type="submit" value="회원정보 수정하기">
	</form>
	
	<%-- 회원 탈퇴 --%>
	<form action="user" method="POST">
		<input type="hidden" name="act" value="userDeactivate">
		<input type="submit" value="회원 탈퇴하기">
	</form>

</body>
</html>