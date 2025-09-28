<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
	<style>
	    main {
	        max-width: 400px;
	        margin: 30px auto;
	    }
	    form {
	        display: flex;
	        flex-direction: column;
	        gap: 10px;
	    }
	    input[type="text"], input[type="password"], input[type="submit"] {
	        padding: 6px;
	        border: 1px solid #ccc;
	    }
	    input[type="submit"] {
	        cursor: pointer;
	    }
	</style>
</head>
<body>
    <%@ include file="/WEB-INF/common/header.jsp" %>

    <main>
        <h1>회원가입</h1>

		<%-- 가입 실패 시 메시지 표시 --%>
        <c:if test="${not empty errorMsg}">
            <div style="color:red;">${errorMsg}</div>
        </c:if>

        <form action="user" method="POST">
            <input type="hidden" name="act" value="userJoin">
            
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" value="${id}" required>

            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
            
            <label for="confirmPassword">비밀번호 재입력</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>

            <input type="submit" value="가입하기">
        </form>
    </main>

    <%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>