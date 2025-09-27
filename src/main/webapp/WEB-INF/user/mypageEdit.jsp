<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
    <h1>회원정보 수정</h1>

    <!-- 로그인하지 않은 경우 -->
    <c:if test="${empty sessionScope.loginUser}">
        <p style="color:red;">로그인 후 이용 가능합니다.</p>
        <a href="user?act=loginForm">로그인 페이지로 이동</a>
    </c:if>

    <!-- 로그인 한 경우 -->
    <c:if test="${not empty sessionScope.loginUser}">
    	<c:if test="${not empty errorMsg}">
    		<p style="color:red;">${errorMsg}</p>
    	</c:if>
    
        <form action="user" method="POST">
            <input type="hidden" name="act" value="userUpdate">

            <p>아이디: <strong>${sessionScope.loginUser.id}</strong></p>
            
            <label for="password">새 비밀번호</label>
            <input type="password" id="password" name="password" required><br><br>
            
            <label for="confirmPassword">비밀번호 확인</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
            
            <input type="submit" value="수정하기">
        </form>

        <p><a href="user?act=userProfile">마이페이지로 돌아가기</a></p>
    </c:if>
</body>
</html>
