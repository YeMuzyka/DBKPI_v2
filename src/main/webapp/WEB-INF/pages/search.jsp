<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Поселення в гуртожиток на час сесії</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<form:form modelAttribute="inputStudent" method="POST" action="result-search" acceptCharset="UTF-8">
		<table>
			<tr>
				<td>Повне ім'я:</td>
				<td><form:input path="studentName"/></td>
			</tr>
			
			<tr>
				<td>Група:</td>
				<td><form:select path="studentGroup" items="${name_group}"></form:select></td>
			</tr>
			
			<tr>
				<td>Початок сесії</td>
				<td><form:select path="sessionStart" items="${session_start}"></form:select></td>
			</tr>
			
			<tr>
				<td>Кінець сесії</td>
				<td><form:select path="sessionEnd" items="${session_end}"></form:select></td>
			</tr>
			
			<tr>
				<td>e-mail:</td>
				<td><form:input path="eMail"/></td>
			</tr>
			
			<tr>
				<td align="right" colspan="2"><input type="submit" value="GO!"></td>
			</tr>
			
		</table>
	</form:form>
</body>
</html>