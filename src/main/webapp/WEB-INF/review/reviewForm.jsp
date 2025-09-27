<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 등록</title>
</head>
<body>

<h1>새 리뷰 등록</h1>

<p>대상 영상 번호: ${vno}</p>
<p>작성자: ${sessionScope.loginUser.id}</p>

<form action="${pageContext.request.contextPath}/review?act=reviewCreate" method="post">
    <input type="hidden" name="vno" value="${vno}">
    
    <label for="title">제목:</label>
    <input type="text" id="title" name="title" required><br><br>

    <label for="content">리뷰 내용:</label><br>
    <textarea id="content" name="content" rows="4" cols="50" required></textarea><br><br>
    
    <button type="submit">등록</button>
    <button type="button" onclick="location.href='${pageContext.request.contextPath}/video?act=videoDetail&no=${vno}'">취소</button>
</form>

</body>
</html>