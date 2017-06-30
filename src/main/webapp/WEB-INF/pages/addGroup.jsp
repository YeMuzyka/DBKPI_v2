<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Нова група</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<form:form modelAttribute="addGroup" method="POST" action="added-group" acceptCharset="UTF-8">
		<table>
			<tr>
				<td>Назва Групи:</td>
				<td><form:input path="id_group"/></td>
			</tr>
			
			<tr>
				<td>Староста групи:</td>
				<td><form:input path="group_vertuhay"/></td>
			</tr>
			
			<tr>
				<td>Курс:</td>
				<td><form:input path="course"/></td>
			</tr>
			
			<tr>
				<td>Початок сесії("yyyy-MM-dd"):</td>
				<td><form:input path="start_ses"/></td>
			</tr>
			
			<tr>
				<td>Кінець сесії("yyyy-MM-dd"):</td>
				<td><form:input path="end_ses"/></td>
				
			</tr>
			
			<tr>
				<td align="right" colspan="2"><input type="submit" value="Додати!"></td>
			</tr>
			
		</table>
	</form:form>
</body>
</html>