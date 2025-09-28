<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영상 등록</title>
</head>
<body>

	<h1>새 운동 영상 등록</h1>
	
	<form action="${pageContext.request.contextPath}/video?act=videoCreate" method="post">
	    <label for="title">제목:</label>
	    <input type="text" id="title" name="title" required><br><br>
	
	    <label for="part">운동 부위:</label>
	    <input type="text" id="part" name="part" required><br><br>
	
	    <label for="url">URL:</label>
	    <input type="url" id="url" name="url" required><br><br>
	    
	    <button type="submit">등록</button>
	    <button type="button" onclick="location.href='${pageContext.request.contextPath}/video?act=list'">취소</button>
	</form>
</body>
</html>