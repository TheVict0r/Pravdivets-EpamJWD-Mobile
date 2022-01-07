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
<body class="d-flex flex-column min-vh-100 fw-light bg-light">
	<jsp:include page="components/header.jsp" />
	<div class="mb-4 "></div>
	<div class="row justify-content-center display-4  mx-auto mb-4 py-5">
		<fmt:message key="change-password.title" />
	</div>
	<div
		class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto text-danger fw-normal">
		<p>
			<c:choose>
				<c:when test="${sessionScope.error == 'missmatched_passwords'}">
					<fmt:message key="change-password.missmatched-passwords" />
				</c:when>
				<c:when test="${sessionScope.error == 'incorrect_password'}">
					<fmt:message key="change-password.incorrect-password" />
				</c:when>
				<c:when test="${sessionScope.error == 'wrong_password'}">
					<fmt:message key="change-password.wrong-old-password" />
				</c:when>
			</c:choose>
		</p>
		<c:remove var="error" />
	</div>
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="POST" action="controller?command=change_password">
			<table>
				<tr>
					<td><label for="old_password" class="form-label"><fmt:message
								key="change-password.old-password" /> </label></td>
					<td><input type="password" class="form-control"
						name="old_password" id="old_password" required minlength="8"></td>
				</tr>
				<tr>
					<td><label for="new_password1" class="form-label"><fmt:message
								key="change-password.new-password1" /> <span
							class="text-primary fw-bold fs-5 ">*</span> </label></td>
					<td><input type="password" class="form-control"
						name="new_password1" id="new_password1" required minlength="8"></td>
				</tr>
				<tr>
					<td><label for="new_password2" class="form-label "><fmt:message
								key="change-password.new-password2" /></label></td>
					<td><input type="password" class="form-control mb-1"
						name="new_password2" id="new_password2" required minlength="8"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="OK"></td>
				</tr>
			</table>
		</form>
	</div>
	<div
		class="row fw-light text-center row justify-content-center mx-auto col-lg-8 mb-5">
		<span> <span class="text-primary fw-bold fs-5 ">*</span> <fmt:message
				key="new-password.password-instruction" />
		</span>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>