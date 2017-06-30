<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Виберіть гуртожиток</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<c:url var="mainHostel" value="/hostel" />
	<form:form modelAttribute="inputHostel" method="POST" action="${mainHostel}" acceptCharset="UTF-8">
		<table>
			<tr>
				<td>Гуртожиток:</td>
				<td><form:select path="id_hostel" items="${id_hostel}"></form:select></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><input type="submit" value="GO!"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>