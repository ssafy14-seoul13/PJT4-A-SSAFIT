<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 영상</title>
</head>
<body>
<h1>운동 영상 상세 정보</h1>

	<c:if test="${not empty video}">
	    <p><strong>번호:</strong> ${video.no}</p>
	    <p><strong>제목:</strong> ${video.title}</p>
	    <p><strong>운동 부위:</strong> ${video.part}</p>
	    <p><strong>URL:</strong> <a href="${video.url}" target="_blank">${video.url}</a></p>
	    
	    <div style="margin: 20px 0;">
	        <iframe width="560" height="315" 
	            src="${video.url}" 
	            frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
	        </iframe>
	    </div>
	
	    <p>
	        <a href="${pageContext.request.contextPath}/video?act=videoEditForm&no=${video.no}">수정</a>
	        
	        <form action="${pageContext.request.contextPath}/video?act=videoDelete" method="post" style="display:inline;">
	            <input type="hidden" name="no" value="${video.no}">
	            <button type="submit" onclick="return confirm('정말로 이 영상을 삭제하시겠습니까?');">삭제</button>
	        </form>
	    </p>
	</c:if>
	
	<c:if test="${empty video}">
	    <p>요청하신 영상을 찾을 수 없습니다.</p>
	</c:if>
	
	<h3>리뷰</h3>
	<c:choose>
	  <c:when test="${empty reviewList}">
	    <p>등록된 리뷰가 없습니다.</p>
	  </c:when>
	  <c:otherwise>
	    <ul>
	      <c:forEach var="rv" items="${reviewList}">
	        <li>
	          <strong>${rv.title}</strong> - ${rv.writer}
	          <br/>
	          ${rv.content}
	          <br/>
	          <a href="${pageContext.request.contextPath}/review?act=reviewEditForm&no=${rv.no}">수정</a>
	          <form action="${pageContext.request.contextPath}/review?act=reviewDelete" method="post" style="display:inline;">
	            <input type="hidden" name="no" value="${rv.no}">
	            <button type="submit" onclick="return confirm('리뷰를 삭제하시겠습니까?');">삭제</button>
	          </form>
	        </li>
	      </c:forEach>
	    </ul>
	  </c:otherwise>
	</c:choose>
	
	<c:if test="${not empty sessionScope.loginUser}">
	  <p>
	    <a href="${pageContext.request.contextPath}/review?act=reviewForm&vno=${video.no}">리뷰 작성</a>
	  </p>
	</c:if>
	
	<p><a href="${pageContext.request.contextPath}/video?act=list">목록으로 돌아가기</a></p>
</body>
</html>