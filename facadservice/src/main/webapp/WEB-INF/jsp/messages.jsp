<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of messages</title>
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of messages</h2>
			</caption>
			<tr>
				<th>guid</th>
				<th>timestamp</th>
				<th>type</th>
				<th>value</th>
				<th>fileName</th>
			</tr>
			<c:forEach items="${messages}" var="message">
				<tr>
					<td>${message.guid}</td>
					<td>${message.timestamp}</td>
					<td>${message.type}</td>
					<td>${message.value}</td>
					<td>${message.fileName}</td>
				</tr>
			</c:forEach>
		</table>
		<br /> <input type="button" value="Back"
			onclick="javascript:history.back()" />
	</div>
</body>
</html>