<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 영상</title>
<style>
/* 기본 설정 */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f9;
	color: #333;
	margin: 20px;
}

.container {
	max-width: 960px;
	margin: 30px auto;
	padding: 25px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

h1 {
	color: #2c3e50;
	border-bottom: 3px solid #e74c3c; /* 피트니스 강조색 */
	padding-bottom: 10px;
	margin-bottom: 20px;
}

h3 {
	color: #2c3e50;
	margin-top: 30px;
	border-bottom: 1px solid #ccc;
	padding-bottom: 5px;
}

/* 영상 정보 스타일 */
.video-info p {
	margin: 5px 0;
	font-size: 1.1em;
}

.video-info strong {
	color: #e74c3c;
	display: inline-block;
	width: 120px;
	font-weight: bold;
}

/* 비디오 임베드 영역 */
.video-embed {
	margin: 20px 0;
	border: 1px solid #ddd;
	border-radius: 8px;
	overflow: hidden;
	/* iframe 반응형 처리를 위한 기본 설정 */
	max-width: 100%;
}

.video-embed iframe {
	display: block;
}

/* 액션 버튼 그룹 (수정/삭제) */
.action-group a, .action-group button, .review-action-group a,
	.review-action-group button {
	padding: 8px 15px;
	margin-right: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-weight: bold;
	text-decoration: none;
	transition: background-color 0.2s;
	display: inline-block;
}

/* 수정 버튼 */
.action-group a {
	background-color: #3498db;
	color: white;
}

.action-group a:hover {
	background-color: #2980b9;
}

/* 삭제 버튼 */
.action-group form button[type="submit"] {
	background-color: #e74c3c;
	color: white;
}

.action-group form button[type="submit"]:hover {
	background-color: #c0392b;
}

/* 리뷰 작성 버튼 */
.review-create-link a {
	background-color: #2ecc71;
	color: white;
	margin-top: 15px;
}

.review-create-link a:hover {
	background-color: #27ae60;
}

/* 리뷰 목록 스타일 */
.review-list {
	list-style: none;
	padding: 0;
	max-height: 400px; /* 리뷰 목록 최대 높이 설정 */
	overflow-y: auto; /* 스크롤 가능하도록 설정 */
	border: 1px solid #eee;
	border-radius: 5px;
	padding: 10px;
	background-color: #fafafa;
}

.review-list li {
	border-bottom: 1px solid #ddd;
	padding: 15px 0;
}

.review-list li:last-child {
	border-bottom: none;
}

.review-list strong {
	color: #34495e;
	font-size: 1.1em;
}

.review-meta {
	font-size: 0.9em;
	color: #7f8c8d;
	margin-top: 5px;
}

/* 목록으로 돌아가기 */
.back-link {
	margin-top: 20px;
	display: block;
}

.back-link a {
	color: #3498db;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container">
		<h1>운동 영상 상세 정보</h1>

		<c:if test="${not empty video}">
			<div class="video-info">
				<p>
					<strong>번호:</strong> ${video.no}
				</p>
				<p>
					<strong>제목:</strong> ${video.title}
				</p>
				<p>
					<strong>운동 부위:</strong> ${video.part}
				</p>
				<p>
					<strong>URL:</strong> <a href="${video.url}" target="_blank">${video.url}</a>
				</p>
			</div>

			<div class="video-embed" style="margin: 20px 0;">
				<iframe width="560" height="315" src="${video.url}" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen> </iframe>
			</div>

			<p class="action-group">
				<a
					href="${pageContext.request.contextPath}/video?act=videoEditForm&no=${video.no}">수정</a>
			<form
				action="${pageContext.request.contextPath}/video?act=videoDelete"
				method="post" style="display: inline;">
				<input type="hidden" name="no" value="${video.no}">
				<button type="submit"
					onclick="return confirm('정말로 이 영상을 삭제하시겠습니까?');">삭제</button>
			</form>
			</p>
		</c:if>

		<c:if test="${empty video}">
			<p class="error-message">요청하신 영상을 찾을 수 없습니다.</p>
		</c:if>

		<c:set var="reviewCount"
			value="${not empty reviewList ? reviewList.size() : 0}" />
		<h3>리뷰 (${reviewCount})</h3>
		<div class="review-section">
			<c:choose>
				<c:when test="${empty reviewList}">
					<p>등록된 리뷰가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<ul class="review-list">
						<c:forEach var="rv" items="${reviewList}">
							<li><strong>${rv.title}</strong> - ${rv.writer} <br />
								${rv.content}
								<div class="review-meta">
									작성일:
									<fmt:formatDate value="${rv.created_at}" pattern="yyyy-MM-dd" />
								</div> <c:if
									test="${not empty sessionScope.loginUser && rv.writer eq String.valueOf(sessionScope.loginUser.id)}">
									<p class="review-action-group">
										<a
											href="${pageContext.request.contextPath}/review?act=reviewEditForm&no=${rv.no}">수정</a>
									<form
										action="${pageContext.request.contextPath}/review?act=reviewDelete"
										method="post" style="display: inline;">
										<input type="hidden" name="no" value="${rv.no}">
										<button type="submit"
											onclick="return confirm('리뷰를 삭제하시겠습니까?');">삭제</button>
									</form>
									</p>
								</c:if></li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>

		<c:if test="${not empty sessionScope.loginUser}">
			<p class="review-create-link">
				<a
					href="${pageContext.request.contextPath}/review?act=reviewForm&vno=${video.no}">리뷰
					작성</a>
			</p>
		</c:if>

		<p class="back-link">
			<a href="${pageContext.request.contextPath}/video?act=list">목록으로
				돌아가기</a>
		</p>
	</div>
</body>
</html>