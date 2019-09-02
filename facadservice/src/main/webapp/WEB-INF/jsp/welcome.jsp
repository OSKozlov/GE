<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<form id="eventsForm" method="POST" action="${contextPath}/events">
	</form>

	<form id="messagesForm" method="POST" action="${contextPath}/messages">
	</form>

	<form id="statisticForm" method="POST"
		action="${contextPath}/statistic"></form>
		
	<div class="container">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>
		</c:if>

		<h3>Menu options:</h3>


		<ul>
			<li><a onclick="document.forms['eventsForm'].submit()">Display
					list of events</a></li>
			<li><a onclick="document.forms['messagesForm'].submit()">Display
					list of messages</a></li>
			<li><a onclick="document.forms['statisticForm'].submit()">Display
					statistic</a></li>
		</ul>
		
	</div>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>