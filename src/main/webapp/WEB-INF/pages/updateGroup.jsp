<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Змінення групи "${updateGroup.id_group}"</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<c:url var="updatedGroup" value="/department/updateGroup?id=${updateGroup.id_group}" />
	<form:form modelAttribute="updateGroup" method="POST" action="${updatedGroup}" acceptCharset="UTF-8">
		<table>
			<tr>
				<td>Назва Групи:</td>
				<td><form:input path="id_group" disabled="true"/></td>
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
				<td align="right" colspan="2"><input type="submit" value="Змінити!"></td>
			</tr>
			
		</table>
	</form:form>
</body>
</html>