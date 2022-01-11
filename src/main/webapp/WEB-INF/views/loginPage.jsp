<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=age" />
<meta name="viewport"
	content="user-scalable=no, inital-scale=1.0, maximum-scale=1.0, minimum-scale=1.0
			width-device-width">
<title>카카오 로그인</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
	<ul>
		<li onclick="kakaoLogin();"><a href="javascript:void(0)"> <span>카카오
					로그인</span>
		</a></li>
	</ul>

	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
		//카카오로그인
		function kakaoLogin() {
			console.log("dd");
			$.ajax({
				url : '/login/getKakaoAuthUrl',
				type : 'get',
				async : false,
				dataType : 'text',
				success : function(res) {
					location.href = res;
					console.log("res == " + res);
				}
			});
		}
	</script>
</body>
</html>