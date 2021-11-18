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

		<div class="row justify-content-center display-4 py-5 mx-auto ">
		<fmt:message key="password-repair.title"/></div>
	<div
		class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto fs-5 mb-4">
		<fmt:message key="password-repair.lead" />
	</div>
		<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		
		<form id="confirmation-code" method="POST" action="controller?command=login">
			<table>
				<tr>
					<td><label for="login" class="form-label"> <fmt:message
								key="login.phone-number" /> <span
							class="text-primary fw-bold fs-5 ">*</span>:
					</label></td>
					<td><input type="text" class="form-control mb-1" name="phone"
						value="" id="phone" required></td>
					<c:remove var="login" />
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="<fmt:message key = "password-repair.get-code"/>"></td>
				</tr>
			</table>
		</form>
	</div>
	
		<div class="row fw-light justify-content-center mx-auto flex-grow-1">
		<span>
			<span class="text-primary fw-bold fs-5 ">*</span>
			<fmt:message key="login.number-format" />
			<b><i>55xxxxxxx, 25xxxxxxx, 29xxxxxxx, 33xxxxxxx, 44xxxxxxx</i></b>
		</span>
	</div>
	
	<jsp:include page="components/footer.jsp" />
	<script src="js/login.js"></script>
</body>
</html>