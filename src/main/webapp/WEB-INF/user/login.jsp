<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
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
	    input[type="text"], input[type="password"] {
	        padding: 6px;
	        border: 1px solid #ccc;
	    }
	    input[type="submit"] {
	        padding: 8px;
	        cursor: pointer;
	    }
	</style>
</head>
<body>
    <%@ include file="/WEB-INF/common/header.jsp" %>

    <main>
        <h1>로그인</h1>
        
		<%-- 로그인 실패 시 메시지 표시 --%>
        <c:if test="${not empty errorMsg}">
            <div style="color:red;">${errorMsg}</div>
        </c:if>

        <form action="user" method="POST">
            <input type="hidden" name="act" value="login">

            <label for="id">아이디</label>
            <input type="text" id="id" name="id" value="${id}" required>

            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="로그인">
        </form>

        <p>아직 회원이 아니신가요? <a href="user?act=userJoinForm">회원가입</a></p>
    </main>

    <%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>