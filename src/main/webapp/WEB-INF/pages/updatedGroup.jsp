<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Змінено групу "${updated.id_group}"</title>
</head>
<body>
	<h2>${updated.id_group} - Групу зміненно!</h2>
	<table>
					
		<tr>
			<td>Староста групи:</td>
			<td>${updated.group_vertuhay}</td>
		</tr>
		
		<tr>
			<td>Курс:</td>
			<td>${updated.course}</td>
		</tr>
		
		<tr>
			<td>Початок сесії("yyyy-MM-dd"):</td>
			<td>${updated.start_ses}</td>
		</tr>
		
		<tr>
			<td>Кінець сесії("yyyy-MM-dd"):</td>
			<td>${updated.end_ses}</td>
		</tr>
		
	</table>
	<c:url var="department" value="/department" />
	<p>Назад на <a href="${department}">Керування студентами</a></p>
</body>
</html>