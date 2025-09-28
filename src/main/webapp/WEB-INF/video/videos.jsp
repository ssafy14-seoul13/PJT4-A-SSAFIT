<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 영상 목록</title>
<style>
/* 기본 설정 */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f9;
	color: #333;
	margin: 20px;
}

h1 {
	color: #2c3e50;
	border-bottom: 3px solid #e74c3c; /* 피트니스 강조색 */
	padding-bottom: 10px;
	margin-bottom: 20px;
}

/* 새 영상 등록 링크 버튼 스타일 */
.register-link {
	display: inline-block;
	padding: 10px 15px;
	background-color: #3498db;
	color: white;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
	margin-bottom: 20px;
	transition: background-color 0.2s;
}

.register-link:hover {
	background-color: #2980b9;
}

/* 테이블 스타일 */
table {
	width: 100%;
	border-collapse: collapse; /* 테이블 경계선 합치기 */
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	background-color: white;
	border-radius: 8px;
	overflow: hidden; /* box-shadow와 border-radius를 잘 보이게 함 */
}

/* 테이블 헤더 */
thead tr {
	background-color: #e74c3c; /* 강조색 배경 */
	color: white;
}

th {
	padding: 12px 15px;
	text-align: left;
}

/* 테이블 폭 설정 (선택된 코드를 CSS로 이동) */
.col-no {
	width: 10%;
}

.col-title {
	width: 50%;
}

.col-part {
	width: 15%;
}

.col-detail {
	width: 25%;
}

/* 테이블 본문 행 */
tbody tr {
	border-bottom: 1px solid #eee;
}

tbody tr:nth-child(even) { /* 짝수 행 배경색 */
	background-color: #f9f9f9;
}

tbody tr:hover { /* 호버 효과 */
	background-color: #f0f0f0;
}

td {
	padding: 12px 15px;
}

/* 영상 링크 스타일 */
td a {
	color: #3498db;
	text-decoration: none;
	font-weight: 500;
}

td a:hover {
	text-decoration: underline;
	color: #e74c3c; /* 호버 시 강조색으로 변경 */
}

/* 등록된 영상 없음 메시지 */
.no-videos {
	padding: 20px;
	text-align: center;
	border: 1px dashed #ccc;
	border-radius: 5px;
	margin-top: 20px;
}
</style>
</head>
<body>
	<h1>전체 영상 목록</h1>

	<p>
		<a href="${pageContext.request.contextPath}/video?act=videoForm"
			class="register-link">새 영상 등록</a>
	</p>

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
							<td class="col-no">${video.no}</td>
							<td class="col-title">${video.title}</td>
							<td class="col-part">${video.part}</td>
							<td class="col-detail"><a
								href="${pageContext.request.contextPath}/video?act=videoDetail&no=${video.no}">영상
									링크</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p class="no-videos">등록된 영상이 없습니다.</p>
		</c:otherwise>
	</c:choose>
</body>
</html>