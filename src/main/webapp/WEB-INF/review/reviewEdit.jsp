<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>리뷰 수정</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
	<style>
	    main {
	        max-width: 600px;
	        margin: 30px auto;
	    }
	    form {
	        display: flex;
	        flex-direction: column;
	        gap: 10px;
	    }
	    input[type="text"], textarea {
	        padding: 6px;
	        border: 1px solid #ccc;
	    }
	    button {
	        padding: 8px;
	        cursor: pointer;
	    }
	</style>
</head>
<body>
    <%@ include file="/WEB-INF/common/header.jsp" %>

    <main>
        <h1>리뷰 수정</h1>

        <c:if test="${not empty review}">
            <p>영상 번호: ${review.vno}</p>
            <p>작성자: ${review.writer}</p>
            <p>등록일: <fmt:formatDate value="${review.created_at}" pattern="yyyy-MM-dd"/></p>
            
            <form action="${pageContext.request.contextPath}/review?act=reviewUpdate" method="post">
                <input type="hidden" name="no" value="${review.no}">
                <input type="hidden" name="vno" value="${review.vno}">

                <label for="title">제목:</label>
                <input type="text" id="title" name="title" value="${review.title}" required>

                <label for="content">내용:</label>
                <textarea id="content" name="content" rows="4" cols="50" required>${review.content}</textarea>
                
                <div>
                    <button type="submit">수정 완료</button>
                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/video?act=videoDetail&no=${review.vno}'">취소</button>
                </div>
            </form>
        </c:if>
    </main>

    <%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
