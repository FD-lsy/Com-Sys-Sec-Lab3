<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>登录页面-</title>
<link rel="stylesheet" href="./css/common.css" />
</head>
<body>
	<div class="wrap login_bg">
		<div class="login_box">
			<div class="login_title">登录</div>
			<form action=" ${pageContext.request.contextPath}/login_servlet"
				method="post">
				<div class="form_text_ipt">
					<input type="text" name="username" required="required" placeholder="用户名">
				</div>
				<div class="form_text_ipt">
					<input type="password" name="password" required="required" placeholder="密码">
				</div>
				<div class="form_btn">
					<input type="submit" name="submit" value="登录">
				</div>
			</form>
		</div>
	</div>
	<div style="text-align: center;"></div>
</body>
</html>
