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

	<div class="row justify-content-center display-4 py-5 mx-auto ">
		<fmt:message key="code-confirmation.title" />
	</div>

	<div class="row align-content-center mx-auto text-danger mb-2">
		<c:if test="${sessionScope.error == 'missmatched_codes'}">
			<fmt:message key="code-confirmation.missmatched-codes" />
			<c:remove var="error" />
		</c:if>
	</div>

	
	
		<div
		class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto fs-5 mb-3">
		<fmt:message key="code-confirmation.lead" />
	</div>
	
	
		<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="POST"
			action="controller?command=password_repair2_enter_code">
			<table>
				<tr>
					<td><label for="entered_code" class="form-label"> <fmt:message
								key="code-confirmation.enter-code" />:
					</label></td>
					<td><input type="text" class="form-control mb-1" name="entered_code"
						 id="entered_code" placeholder="1234" pattern="^[0-9]{4}$" required></td>
				</tr>
				<tr>
				<td></td>
				<td><input type="submit" class="btn btn-outline-dark" value="OK"></td>
			</tr>
			</table>
		</form>
		</div>
	
	<jsp:include page="components/footer.jsp" />
</body>
</html>