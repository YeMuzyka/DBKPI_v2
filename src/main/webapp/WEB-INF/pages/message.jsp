<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message To Hostel</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<c:url var="mainHostel" value="/hostel" />
	<h3>${result}</h3>
	<p>Назад на <a href="${mainHostel}">Керування гуртожитком</a></p>
</body>
</html>