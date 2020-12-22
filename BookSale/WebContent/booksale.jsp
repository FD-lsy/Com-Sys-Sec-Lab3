<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="database.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Sale</title>
<link rel="stylesheet" href="./css/common.css" />
</head>
<body>

	<form action="${pageContext.request.contextPath}/booksearch"
		method="post">
		<div class="table_a">
			<table>
				<tr>
					<th>序列号</th>
					<th>书名</th>
					<th>价格</th>
					<th>剩余数量</th>
					<td><div class="form_sub">
							<input type="submit" name="submit" value="查询"
								placeholder="查询所有库存书籍">
						</div></td>
				</tr>

				<c:forEach items="${requestScope.booklist}" var="book">
					<tr>
						<td><c:out value="${book.getId()}" /></td>
						<td><c:out value="${book.getName()}" /></td>
						<td><c:out value="${book.getPrice()}" /></td>
						<td><c:out value="${book.getRemain()}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>

	<form action="${pageContext.request.contextPath}/bookadd" method="post">
		<div class="form_text_ipt">
			<input name="name" type="text" required="required" placeholder="书名">
		</div>

		<div class="form_text_ipt">
			<input name="price" type="text" required="required" placeholder="价格">
		</div>

		<div class="form_text_ipt">
			<input name="remain" type="text" required="required"
				placeholder="剩余数量">
		</div>
		<div class="form_sub">
			<input type="submit" name="submit" value="添加">
		</div>
	</form>
	<a href="login.jsp">返回登录页面</a>
</body>
</html>