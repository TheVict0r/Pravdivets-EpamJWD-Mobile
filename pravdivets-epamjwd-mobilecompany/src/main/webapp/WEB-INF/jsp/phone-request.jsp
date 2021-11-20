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
	<c:choose>
		<c:when test="${sessionScope.mode == 'sign_up'}">
			<div class="mb-5 "></div>
			<div class="row justify-content-center display-4 mx-auto mb-3 py-5">
				<fmt:message key="sign-up.title" />
			</div>
			<div
				class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto fs-5 mb-3">
				<fmt:message key="sign-up.lead" />
			</div>
		</c:when>
		<c:when test="${sessionScope.mode == 'password_repair'}">
					<div class="mb-5 "></div>
			<div class="row justify-content-center display-4 mx-auto mb-5 py-5">
				<fmt:message key="password-repair.title" />
			</div>
		</c:when>
	</c:choose>
	<c:if test="${sessionScope.error == 'wrong_phone'}">
		<div class="row align-content-center mx-auto text-danger mb-2">
			<fmt:message key="password-repair.check-phone" />
			<c:remove var="error" />
		</div>
	</c:if>
	<c:if test="${sessionScope.error == 'already_signed_up'}">
		<div class="row align-content-center mx-auto text-danger mb-2">
			<span><fmt:message key="phone-request.already-signed-up" />
			<a	href="${pageContext.request.contextPath}/controller?command=go_to_password_repair_page" class="card-link"><fmt:message key="login.forgot-password"/></a>
			<c:remove var="error" /> </span>
		</div>
	</c:if>
	<div class="row justify-content-center mx-auto fw-light">
		<form id="check-phone" method="POST"
			action="controller?command=check_phone">
			<table>
				<tr>
					<td><label for="phone" class="form-label"> <fmt:message
								key="login.phone-number" />:
					</label></td>
					<td><input type="text" class="form-control" name="phone"
						value="${sessionScope.phone}" id="phone"
						pattern="^(25|29|33|44|55)[0-9]{7}$" required></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="OK"></td>
				</tr>
				
			</table>
		</form>
	</div>
	<div
		class="row fw-light row justify-content-center mx-auto py-3 flex-grow-1">
		<span> <fmt:message key="login.number-format" /> <b><i>55xxxxxxx,
					25xxxxxxx, 29xxxxxxx, 33xxxxxxx, 44xxxxxxx</i></b></span>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>