<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 영상 목록</title>
</head>
<body>
	<h1>전체 영상 목록</h1>
	
	<p><a href="${pageContext.request.contextPath}/video?act=videoForm">새 영상 등록</a></p>
	
	<c:choose>
		<c:when test="${not empty videoList }">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>부위</th>
						<th>영상 링크</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="video" items="${videoList}">
						<tr>
							<td>${video.no}</td>
							<td>${video.title}</td>
							<td>${video.part}</td>					
							<td><a href="${pageContext.request.contextPath}/video?act=videoDetail&no=${video.no}">영상 링크</a></td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>등록된 영상이 없습니다.</p>
		</c:otherwise>
	</c:choose>
</body>
</html>