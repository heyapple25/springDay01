<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
input[type="text"]{
	border:0;
}
input[type="submit"]{
	float:left
}
button{
	margin-left:10px;
}
</style>
<meta charset="EUC-KR">
</head>
<body>
<form action="update.do" method="POST">
	<input type="hidden" name="seq" value="${bData.seq }"/>
	<table border="1" >
		<tr><th>글 번호</th><td>${bData.seq }</td></tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${bData.title }"/></td>
		</tr>
		<tr><th>작가</th><td>${bData.writer }</td></tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content" value="${bData.content }"/></td>
		</tr>
		<tr><th>등록 날짜</th><td>${bData.regDate }</td></tr>
		<tr><th>조회수</th><td>${bData.cnt }</td></tr>
	</table>
<input type="submit" value='수정'/>
</form>
<button onclick="location.href='delete.do?seq=${bData.seq}'">삭제</button><br>
<a href="list.do">목록으로 되돌아가기</a>
</body>
</html>