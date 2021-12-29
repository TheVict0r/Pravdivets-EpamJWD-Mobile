<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale
	value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
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
	<div class="row justify-content-center display-4 mx-auto py-3 mb-2 ">
		<fmt:message key="add-consultant.new-consultant" />
	</div>
		<div
		class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto text-danger fw-normal">
		<p>
			<c:choose>
				<c:when test="${sessionScope.error == 'missmatched_passwords'}">
					<fmt:message key="new-password.missmatched-passwords" />
				</c:when>
				<c:when test="${sessionScope.error == 'incorrect_password'}">
					<fmt:message key="new-password.incorrect-password" />
				</c:when>
			</c:choose>
		</p>
		<c:remove var="error" />
	</div>
	
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="post" action=controller?command=add_consultant>
			<table>
				<tr>
					<td><label for="last_name" class="form-label"><fmt:message
								key="add-consultant.last-name" />:</label></td>
					<td><input type="text" class="form-control"
						name="last_name" id="last_name" placeholder="Иванов"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
						value="${sessionScope.consultant_last_name}" required> <c:remove
							var="consultant_last_name" /></td>
				</tr>
				<tr>
					<td><label for="first_name" class="form-label"><fmt:message
								key="add-consultant.first-name" />:</label></td>
					<td><input type="text" class="form-control"
						name="first_name" id="first_name" placeholder="Иван"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
						value="${sessionScope.consultant_first_name}" required> <c:remove
							var="consultant_first_name" /></td>
				</tr>
				<tr>
					<td><label for="middle_name" class="form-label"><fmt:message
								key="add-consultant.middle-name" />:</label></td>
					<td><input type="text" class="form-control"
						name="middle_name" id="middle_name"
						placeholder="Иванович"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
						value="${sessionScope.consultant_middle_name}"> <c:remove
							var="consultant_middle_name" /></td>
				</tr>
				<tr>
					<td><label for="passport" class="form-label"><fmt:message
								key="add-consultant.passport" /></label></td>
					<td><input type="text" class="form-control" name="passport"
						id="passport" placeholder="AB1234567" pattern="^[A-Z]{2}[0-9]{7}$"
						value="${sessionScope.passport}" required>
					<c:remove var="passport" /></td>
				</tr>
				<tr>
					<td><label for="email" class="form-label">e-mail:</label></td>
					<td><input type="email" class="form-control" name="email"
						id="email" placeholder="ivanov@mail.com"
						value="${sessionScope.email}" required> <c:remove
							var="email" /></td>
				</tr>
				<tr>
					<td><label for="password1" class="form-label"><fmt:message
								key="add-consultant.password1" /> <span
							class="text-primary fw-bold fs-5 ">*</span> </label></td>
					<td><input type="password" class="form-control"
						name="password1" id="password1" required minlength="8"></td>
					<c:remove var="password1" />
				</tr>
				<tr>
					<td><label for="password2" class="form-label "><fmt:message
								key="add-consultant.password2" /></label></td>
					<td><input type="password" class="form-control mb-1"
						name="password2" id="password2" required minlength="8"></td>
					<c:remove var="password2" />
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="OK"></td>
				</tr>
			</table>
		</form>
	</div>

	<div class="row fw-light text-center row justify-content-center mx-auto col-lg-8 mb-3 ">
		<span> <span class="text-primary fw-bold fs-5 ">*</span> <fmt:message
				key="new-password.password-instruction" />
		</span>
	</div>
	<div class="d-grid col-3 py-1 mb-3 mx-auto ">
		<a class="btn btn-outline-dark"
			href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
				key="add-subscriber.back" /></a>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>