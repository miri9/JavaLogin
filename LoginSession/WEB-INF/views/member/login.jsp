<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<p>${param.result == "fail"?"아이디나 패스워드 오류": (param.result == "fail2"?"로그인이 필요한 페이지입니다.":"")}</p>
<%-- 새로고침 했을 때 계속 오류메시지 뜰 경우  > js 등으로 브라우저 히스토리 제어 필요. --%>
	<form method="post">
		<input type="text" name="mid" value="u1">
		<input type="text" name="mpw" value="u1">
		<button>로그인</button>
	</form>
</body>
</html>