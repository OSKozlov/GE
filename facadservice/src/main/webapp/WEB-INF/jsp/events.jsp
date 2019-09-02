<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of events</h2></caption>
            <tr>
                <th>id</th>
                <th>timestamp</th>
                <th>topic</th>
                <th>type</th>
                <th>fileName</th>
            </tr>
            <c:forEach var="event" items="${events}">
                <tr>
                    <td><c:out value="${event.id}" /></td>
                    <td><c:out value="${event.timestamp}" /></td>
                    <td><c:out value="${event.topic}" /></td>
                    <td><c:out value="${event.type}" /></td>
                    <td><c:out value="${event.fileName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>