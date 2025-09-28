<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영상 등록</title>
<style>
/* 기본 설정 */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f9;
	color: #333;
	margin: 20px;
}

.form-container {
	max-width: 600px;
	margin: 50px auto;
	padding: 30px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

h1 {
	color: #2c3e50;
	border-bottom: 3px solid #e74c3c; /* 피트니스 강조색 */
	padding-bottom: 10px;
	margin-bottom: 30px;
}

/* 폼 요소 스타일 */
form label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
	color: #555;
}

form input[type="text"], form input[type="url"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box; /* 패딩이 폭에 포함되도록 설정 */
}

/* 버튼 그룹 */
.button-group button {
	padding: 10px 20px;
	margin-right: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-weight: bold;
	transition: background-color 0.2s;
}

/* 등록 버튼 (강조색) */
form button[type="submit"] {
	background-color: #3498db;
	color: white;
}

form button[type="submit"]:hover {
	background-color: #2980b9;
}

/* 취소 버튼 (보조색) */
form button[type="button"] {
	background-color: #bdc3c7;
	color: #333;
}

form button[type="button"]:hover {
	background-color: #95a5a6;
}
</style>
</head>
<body>
	<div class="form-container">
		<h1>새 운동 영상 등록</h1>

		<form
			action="${pageContext.request.contextPath}/video?act=videoCreate"
			method="post">
			<label for="title">제목:</label> <input type="text" id="title"
				name="title" required><br>
			<br> <label for="part">운동 부위:</label> <input type="text"
				id="part" name="part" required><br>
			<br> <label for="url">URL:</label> <input type="url" id="url"
				name="url" required><br>
			<br>

			<div class="button-group">
				<button type="submit">등록</button>
				<button type="button"
					onclick="location.href='${pageContext.request.contextPath}/video?act=list'">취소</button>
			</div>
		</form>
	</div>
</body>
</html>