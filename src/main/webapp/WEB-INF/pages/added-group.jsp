<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Нова група</title>
</head>
<body>
	<h2>Нову групу додано:</h2>
	<table>
		<tr>
			<td>Назва Група:</td>
			<td>${addedGroup.id_group}</td>
		</tr>
		
		<tr>
			<td>Староста групи:</td>
			<td>${addedGroup.group_vertuhay}</td>
		</tr>
			
		<tr>
			<td>Курс:</td>
			<td>${addedGroup.course}</td>
		</tr>
			
		<tr>
			<td>Початок сесії:</td>
			<td>${addedGroup.start_ses}</td>
		</tr>
			
		<tr>
			<td>Кінець сесії:</td>
			<td>${addedGroup.end_ses}</td>		
		</tr>
	</table>
	<c:url var="department" value="/department" />
	<p>Назад на <a href="${department}">Керування студентами</a></p>
</body>
</html>