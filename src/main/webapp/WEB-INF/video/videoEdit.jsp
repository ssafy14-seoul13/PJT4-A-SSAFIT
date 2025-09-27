<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영상 수정</title>
</head>
<body>

	<h1>운동 영상 수정</h1>
	
	<c:if test="${not empty video}">
	    <form action="${pageContext.request.contextPath}/video?act=videoUpdate" method="post">
	        <input type="hidden" name="no" value="${video.no}">
	        
	        <p><strong>번호:</strong> ${video.no}</p>
	        
	        <label for="title">제목:</label>
	        <input type="text" id="title" name="title" value="${video.title}" required><br><br>
	
	        <label for="part">운동 부위:</label>
	        <input type="text" id="part" name="part" value="${video.part}" required><br><br>
	
	        <label for="url">URL:</label>
	        <input type="url" id="url" name="url" value="${video.url}" required><br><br>
	        
	        <button type="submit">수정 완료</button>
	        <button type="button" onclick="location.href='${pageContext.request.contextPath}/video?act=videoDetail&no=${video.no}'">취소</button>
	    </form>
	</c:if>
	<c:if test="${empty video}">
	    <p>수정할 영상을 찾을 수 없습니다.</p>
	    <p><a href="${pageContext.request.contextPath}/video?act=list">목록으로 돌아가기</a></p>
	</c:if>

</body>
</html>