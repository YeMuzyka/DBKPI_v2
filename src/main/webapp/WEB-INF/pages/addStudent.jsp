<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Новий студент</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<c:url var="addStudent" value="/department/show/addStudent?id=${id}" />
	<form:form modelAttribute="addStudent" method="POST" action="${addStudent}" acceptCharset="UTF-8">
		<table>
		
			<tr>
				<td>№ залікової книжки:</td>
				<td><form:input path="id_student"/></td>
			</tr>
			
			<tr>
				<td>Повне ім'я:</td>
				<td><form:input path="full_name"/></td>
			</tr>
			
			<tr>
				<td>Дата народження:</td>
				<td><form:input path="date_birth"/></td>
			</tr>
			
			<tr>
				<td>Стать:</td>
				<td><form:select path="male_female" items="${gender}"/></td>
			</tr>
			
			<tr>
				<td>Необхідність гуртожитку:</td>
				<td><form:select path="need_hostel" items="${choose}"/></td>
			</tr>
			
			<tr>
				<td align="right" colspan="2"><input type="submit" value="Додати!"></td>
			</tr>
			
		</table>
	</form:form>
</body>
</html>