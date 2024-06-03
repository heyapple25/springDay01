<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 목록</title>
</head>
<body>
	<h1> 게시판 </h1>
	<table  border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>등록일</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="d" items="${bList}">
		<tr>
			<td>${d.seq}</td>
			<td><a href="detail.do?seq=${d.seq}">${d.title}</a></td>
			<td>${d.writer}</td>
			<td>${d.regDate}</td>
			<td>${d.cnt}</td>
		</tr>
	</c:forEach>
	</table>
	<a href="insert.do">입력</a>
</body>
</html>