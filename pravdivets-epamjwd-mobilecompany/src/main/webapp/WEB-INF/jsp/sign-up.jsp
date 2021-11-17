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
	<div class="row justify-content-center display-4  mx-auto mb-4 ">
		<fmt:message key="sign-up.title" />
	</div>
	<div
		class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto fs-5 mb-3">
		<fmt:message key="sign-up.lead" />
	</div>
	<div
		class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto text-danger fw-normal">
		<p>
			<c:choose>
				<c:when test="${sessionScope.error == 'missmatched_passwords'}">
					<fmt:message key="sign-up.missmatched-passwords" />
				</c:when>
				<c:when test="${sessionScope.error == 'incorrect_password'}">
					<fmt:message key="sign-up.incorrect-password" />
				</c:when>
				<c:when test="${sessionScope.error == 'already_signed_up'}">
					<fmt:message key="sign-up.already-signed-up" />
				</c:when>
				<c:when test="${sessionScope.error == 'no_user'}">
					<fmt:message key="sign-up.no-user" />
				</c:when>
			</c:choose>
		</p>
		<c:remove var="error" />
	</div>
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="POST" action="controller?command=signup">
			<table>
				<tr>
					<td><label for="phone" class="form-label"> <fmt:message
								key="sign-up.phone" /> <span class="text-primary fw-bold fs-5 ">*</span>
					</label></td>
					<td><input type="text" class="form-control" name="phone"
						value="${sessionScope.phone}" id="phone" placeholder="551234567"
						pattern="^(25|29|33|44|55)[0-9]{7}$" required></td>
					<c:remove var="phone" />
				</tr>
				<tr>
					<td><label for="password1" class="form-label"><fmt:message
								key="sign-up.password1" /> <span
							class="text-primary fw-bold fs-5 ">**</span> </label></td>
					<td><input type="password" class="form-control"
						name="password1" id="password1" required minlength="8"></td>
					<c:remove var="password1" />
				</tr>
				<tr>
					<td><label for="password2" class="form-label "><fmt:message
								key="sign-up.password2" /></label></td>
					<td><input type="password" class="form-control "
						name="password2" id="password2" required minlength="8"></td>
					<c:remove var="password2" />
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="<fmt:message key = "sign-up.signup"/>"></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="row fw-light row justify-content-center mx-auto ">
		<span> <span class="text-primary fw-bold fs-5 ">*</span> <fmt:message
				key="login.number-format" /> <b><i>55xxxxxxx, 25xxxxxxx,
					29xxxxxxx, 33xxxxxxx, 44xxxxxxx</i></b>
		</span>
	</div>
	<div class="row fw-light row justify-content-center mx-auto mb-5">
		<span> <span class="text-primary fw-bold fs-5 ">**</span> <fmt:message
				key="sign-up.password-instruction" />
		</span>
	</div>
	<jsp:include page="components/footer.jsp" />
	<script src="js/login.js"></script>
</body>
</html>