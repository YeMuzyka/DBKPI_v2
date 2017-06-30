<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Студенти із групи "${id}"</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<c:url var="department" value="/department" />
	<p>Назад на <a href="${department}">Керування студентами</a></p>
	<table style="border: 1px solid; width: 1000px; text-align:center">
 			<thead style="background:#fcf">
 				<tr>
 					<th>Залік. Книжка</th>
 					<th>Ім'я</th>
 					<th>Дата Народ.</th>
 					<th>Стать</th>
 					<th>Необ. Гуртож.</th>
 					<th>Група</th>
 					<th colspan="3" />
 				</tr>
 			</thead>
 			<tbody>
 				<c:forEach items="${students}" var="s">
 					<c:url var="addStudent" value="/department/show/addStudent?id=${id}"/>
 					<c:url var="updateStudent" value="/department/show/updateStudent?id=${s.id_student}" />
 					<c:url var="deleteStudent" value="/department/show/deleteStudent?id=${s.id_student}" />
 					<tr>
 						<td nowrap="nowrap" ><c:out value="${s.id_student}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${s.full_name}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${s.date_birth}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${s.male_female}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${s.need_hostel}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${id}"></c:out></td>
 						<td><a href="${addStudent}">Додати</a></td>
 						<td><a href="${updateStudent}">Змінити</a></td>
 						<td><a href="${deleteStudent}">Видалити</a></td>
 					</tr>
 				</c:forEach>
 			</tbody>
 	</table>
 	<c:if test="${empty students}">
 		Список студентів для данної групи пустий. <a href="${addStudent}">Додати</a> a book.
	</c:if>
</body>
</html>