<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Студента додано</title>
</head>
<body>
	<h2>Студента додано до групи: ${id}</h2>
	<table>
	
		<tr>
			<td>№ залікової книжки:</td>
			<td>${student.id_student}</td>
		</tr>
		
		<tr>
			<td>Повне ім'я:</td>
			<td>${student.full_name}</td>
		</tr>
		
		<tr>
			<td>Дата народження:</td>
			<td>${student.date_birth}</td>
		</tr>
		
		<tr>
			<td>Стать:</td>
			<td>${student.male_female}</td>
		</tr>
		
		<tr>
			<td>Необхідність гуртожитку:</td>
			<td>${student.need_hostel}</td>
		</tr>
		
		<tr>
			<td>Група:</td>
			<td>${id}</td>
		</tr>
		
	</table>
	<c:url var="add" value="/department/show?id=${id}" />
	<p>Назад на студентів із групи: <a href="${add}">"${id}"</a></p>
	<c:url var="department" value="/department" />
	<p>Назад на <a href="${department}">Керування студентами</a></p>
</body>
</html>