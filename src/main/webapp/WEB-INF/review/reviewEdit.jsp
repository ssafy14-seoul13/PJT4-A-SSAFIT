<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
</head>
<body>

<h1>리뷰 수정</h1>

<c:if test="${not empty review}">
    <p>영상 번호: ${review.vno}</p>
    <p>작성자: ${review.writer}</p>
    <p>등록일: <fmt:formatDate value="${review.created_at}" pattern="yyyy-MM-dd"/></p>
    
    <form action="${pageContext.request.contextPath}/review?act=reviewUpdate" method="post">
        <input type="hidden" name="no" value="${review.no}">
        <input type="hidden" name="vno" value="${review.vno}">

        <label for="title">제목:</label>
        <input type="text" id="title" name="title" value="${review.title}" required><br><br>

        <label for="content">내용:</label><br>
        <textarea id="content" name="content" rows="4" cols="50" required>${review.content}</textarea><br><br>
        
        <button type="submit">수정 완료</button>
        <button type="button" onclick="location.href='${pageContext.request.contextPath}/video?act=videoDetail&no=${review.vno}'">취소</button>
    </form>
</c:if>

</body>
</html>s