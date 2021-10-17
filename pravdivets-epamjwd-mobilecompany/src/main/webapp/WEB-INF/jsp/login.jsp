<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale
	value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}" />
<fmt:setBundle basename="language" />


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

	<jsp:include page="components/header.jsp" />
	<div class="mb-4 "></div>
	<div class="row align-content-center mx-auto flex-grow-1">
		<c:if test="${requestScope.session_time_out eq 'session_time_out'}">
			<p class="text-danger fs-5 ">
				<fmt:message key="login.time.out" />
			</p>
			<c:remove var="session_time_out" />
		</c:if>
	</div>
		<div class="row justify-content-center display-4  mx-auto mb-5 "><fmt:message key="login.signin"/></div>
		<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="POST" action="controller?command=login">
			<table>
				<c:if test="${sessionScope.error eq 'error_login'}">
					<p class="text-danger">
						<fmt:message key="login.check.data" />
					</p>
					<c:remove var="error" />
				</c:if>
				<tr>
					<td><label for="login" class="form-label"> <fmt:message
								key="login.phone.number" /> <span
							class="text-primary fw-bold fs-5 ">*</span> / e-mail:
					</label></td>
					<td><input type="text" class="form-control mb-1" name="login"
						value="${sessionScope.login}" id="login" required></td>
					<c:remove var="login" />
				</tr>
				<tr>
					<td><label for="password" class="form-label"><fmt:message
								key="login.password" />:</label></td>
					<td><input type="password" class="form-control"
						name="password" value="${sessionScope.password}" id="password"
						required></td>
					<c:remove var="password"/>
				</tr>
				<tr>
					<td></td>
					<td class="fw-light "><input class="form-check-input mb-4"
						type="checkbox" onclick="showPassword()"> <fmt:message
							key="login.show.password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="<fmt:message key = "login.signin"/>"></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="row fw-light row justify-content-center mx-auto flex-grow-1">
		<span>
			<span class="text-primary fw-bold fs-5 ">*</span>
			<fmt:message key="login.number.format" />
			<b><i>55xxxxxxx, 25xxxxxxx, 29xxxxxxx, 33xxxxxxx, 44xxxxxxx</i></b>
		</span>
	</div>
	<jsp:include page="components/footer.jsp" />
	<script src="js/login.js"></script>
</body>
</html>