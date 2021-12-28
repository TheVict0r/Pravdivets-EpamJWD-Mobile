<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="${sessionScope.session_locale}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">
	<jsp:include page="components/header.jsp" />
	<div class="row justify-content-center display-4 mx-auto py-5 mb-3 ">
		<fmt:message key="add-consultant.new-consultant" />
	</div>
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="post" action=controller?command=add_consultant>
			<table>
				<tr>
					<td><label for="user_last_name" class="form-label"><fmt:message
								key="add-consultant.last-name" />:</label></td>
					<td><input type="text" class="form-control"
						name="user_last_name" id="user_last_name" placeholder="Иванов"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
						value="${sessionScope.user_last_name}" required>
						<c:remove var="user_last_name" /></td>
				</tr>
				<tr>
					<td><label for="user_first_name" class="form-label"><fmt:message
								key="add-consultant.first-name" />:</label></td>
					<td><input type="text" class="form-control"
						name="user_first_name" id="user_first_name" placeholder="Иван"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
						value="${sessionScope.user_first_name}" required>
						<c:remove var="user_first_name" /></td>
				</tr>
				<tr>
					<td><label for="user_middle_name"
						class="form-label"><fmt:message
								key="add-consultant.middle-name" />:</label></td>
					<td><input type="text" class="form-control"
						name="user_middle_name"
						id="user_middle_name" placeholder="Иванович"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
						value="${sessionScope.user_middle_name}"> <c:remove
							var="user_middle_name" /></td>
				</tr>
				<tr>
					<td><label for="passport" class="form-label"><fmt:message
								key="add-consultant.passport" /></label></td>
					<td><input type="text" class="form-control" name="passport"
						id="passport" placeholder="AB1234567" pattern="^[A-Z]{2}[0-9]{7}$"
						value="${sessionScope.passport}" required><c:remove
							var="passport" /></td>
				</tr>
				<tr>
					<td><label for="email" class="form-label">e-mail:</label></td>
					<td><input type="email" class="form-control" name="email"
						id="email" placeholder="ivanov@mail.com" 
						value="${sessionScope.email}" required> <c:remove
							var="email" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="OK"></td>
				</tr>
			</table>

		</form>
			<div class="container py-3 mb-1">
				<div class="row justify-content-between">
					<div class="col-4">
						<a class="btn btn-outline-dark"
							href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
								key="add-subscriber.back" /></a>
				</div>
			</div>
			</div>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>