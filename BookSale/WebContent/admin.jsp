<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="database.Priv"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限管理</title>
<link rel="stylesheet" href="./css/common.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath}/table_priv"
		method="post">
		<div class="table_a">
			<table>
				<tr>
					<th>用户</th>
					<th>Select</th>
					<th>Insert</th>
					<th>update</th>
					<th><div class="form_sub">
							<input type="submit" name="submit" value="查询">
						</div></th>
				</tr>

				<c:forEach items="${requestScope.privlist}" var="priv">
					<tr>
						<td><c:out value="${priv.getUser()}" /></td>
						<td><c:out value="${priv.getMap().get('Select')}" /></td>
						<td><c:out value="${priv.getMap().get('Insert')}" /></td>
						<td><c:out value="${priv.getMap().get('Update')}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<form action="${pageContext.request.contextPath}/table_update"
		method="post">
		<div class="form_text_ipt">
			<input name="User" type="text" required="required" placeholder="用户名">
		</div>

		<div class="form_text_ipt">
			<input name="priv" type="text" placeholder="用首字母小写表示权限">
		</div>
		<div class="form_text_ipt">
			<input type="text" name="operation" placeholder="填写添加或删除">
		</div>
		<div class="form_sub">
			<input type="submit" name="submit" value="确认">
		</div>
	</form>

	<form action="${pageContext.request.contextPath}/column_priv"
		method="post">
		<div class="table_a">
			<table>
				<tr>
					<th>用户</th>
					<th>列</th>
					<th>Select</th>
					<th>Insert</th>
					<th>update</th>
					<th><div class="form_sub">
							<input type="submit" name="submit" value="查询">
						</div></th>
				</tr>

				<c:forEach items="${requestScope.privlist2}" var="priv">
					<tr>
						<td><c:out value="${priv.getUser()}" /></td>
						<td><c:out value="${priv.getColumnName()}" /></td>
						<td><c:out value="${priv.getMap().get('Select')}" /></td>
						<td><c:out value="${priv.getMap().get('Insert')}" /></td>
						<td><c:out value="${priv.getMap().get('Update')}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	 
	<form action="${pageContext.request.contextPath}/column_update"
		method="post" class="fl">
		<div class="form_text_ipt">
			<input name="User" type="text" required="required" placeholder="用户名">
		</div>
		<div class="form_text_ipt">
			<input name="columnName" type="text" required="required"
				placeholder="列名">
		</div>
		<div class="form_text_ipt">
			<input name="priv" type="text" placeholder="用首字母小写表示权限">
		</div>
		<div class="form_text_ipt">
			<input type="text" name="operation" placeholder="填写添加或删除">
		</div>
		<div class="form_sub">
			<input type="submit" name="submit" value="确认">
		</div>
	</form>
	<a href="login.jsp">返回登录页面</a>
</body>
</html>