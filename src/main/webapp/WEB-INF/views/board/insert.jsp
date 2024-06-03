<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판</title>
</head>
<body>
<h1>게시글 입력</h1>
<form action="insert.do" method="POST">
<table border="1">
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" required/></td>
	</tr>
	<tr>
		<th>작가</th>
		<td><input type="text" name="writer"  required/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="content" cols="30" rows="15" required></textarea>
		</td>
	</tr>
</table>
<input type="submit" value="입력"/>
</form>
</body>
</html>