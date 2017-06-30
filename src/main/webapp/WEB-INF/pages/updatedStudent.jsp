<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Дані про студента змінено</title>
</head>
<body>
	<table>
	
		<tr>
			<td>№ залікової книжки:</td>
			<td>${updatedStudent.id_student}</td>
		</tr>
		
		<tr>
			<td>Повне ім'я:</td>
			<td>${updatedStudent.full_name}</td>
		</tr>
		
		<tr>
			<td>Дата народження:</td>
			<td>${updatedStudent.date_birth}</td>
		</tr>
		
		<tr>
			<td>Стать:</td>
			<td>${updatedStudent.male_female}</td>
		</tr>
		
		<tr>
			<td>Необхідність гуртожитку:</td>
			<td>${updatedStudent.need_hostel}</td>
		</tr>
		
		<tr>
			<td>Група:</td>
			<td>${updatedStudent.id_group.id_group}</td>
		</tr>
		
	</table>
	<c:url var="add" value="/department/show?id=${updatedStudent.id_group.id_group}" />
	<p>Назад на студентів із групи: <a href="${add}">"${updatedStudent.id_group.id_group}"</a></p>
	<c:url var="department" value="/department" />
	<p>Назад на <a href="${department}">Керування студентами</a></p>
</body>
</html>