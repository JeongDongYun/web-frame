<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test = "${errors.notMatch}"> 아이디와 암호가 일치하지 않습니다. </c:if>
	
	<form action="ch03/login/login.do" method="post">
		이메일 : <br> <input type="text" name="email"> <c:if test="${errors.email}"> 이메일을 입력하세요.</c:if>
		<p>
			암호 : <br> <input type="password" name="password"><c:if test="${errors.password}"> 암호를 입력하세요.</c:if>
		<p>
			<input type="submit" value="로그인">
	</form>

</body>
</html>