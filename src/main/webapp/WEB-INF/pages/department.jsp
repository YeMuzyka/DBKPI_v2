<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Керування студентами</title>
</head>
<body>
	<fmt:requestEncoding value="utf-8"/>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Користувач : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Вийти</a>
		</h2>
	</c:if>
	<c:url var="addGroup" value="/department/addGroup" />
		<table style="border: 1px solid; width: 1000px; text-align:center">
 			<thead style="background:#fcf">
 				<tr>
 					<th>Група</th>
 					<th>Староста</th>
 					<th>Курс</th>
 					<th>Початок</th>
 					<th>Кінець</th>
 					<th colspan="3" />
 				</tr>
 			</thead>
 			<tbody>
 				<c:forEach items="${all_group}" var="group">
 					<c:url var="updateGroup" value="/department/updateGroup?id=${group.id_group}"/>
 					<c:url var="showStudent" value="/department/show?id=${group.id_group}" />
 					<tr>
 						<td nowrap="nowrap" ><c:out value="${group.id_group}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${group.group_vertuhay}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${group.course}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${group.start_ses}"></c:out></td>
 						<td nowrap="nowrap"><c:out value="${group.end_ses}"></c:out></td>
 						<td><a href="${addGroup}">Додати</a></td>
 						<td><a href="${updateGroup}">Змінити</a></td>
 						<td><a href="${showStudent}">Студенти</a></td>
 					</tr>
 				</c:forEach>
 			</tbody>
 		</table>
 		<c:if test="${empty all_group}">
 			Список груп пустий. <a href="${addGroup}">Додати</a> a book.
		</c:if>
</body>
</html>