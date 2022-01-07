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
	<div class="row justify-content-center display-4 mx-auto py-4 mb-4">
		<fmt:message key="add-service.new-service" />
	</div>
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="post" action=controller?command=add_service>
			<table>
				<tr>
					<td><label for="name" class="form-label"><fmt:message
								key="add-service.name" />:</label></td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" name="name"
						id="name" value="${sessionScope.name}" required> <c:remove
							var="name" /></td>
				</tr>
				<tr>
					<td><label for="description" class="form-label"><fmt:message
								key="add-service.description" />:</label></td>
				</tr>
				<tr>
					<td><textarea class="form-control" name="description"
							id="description" rows="2" cols="50" required>
						${sessionScope.description} 
						</textarea> <c:remove var="description" /></td>
				</tr>
				<tr>
					<td><label for="tariff" class="form-label"><fmt:message
								key="add-service.tariff" />:</label></td>
				</tr>
				<tr>
					<td><input type="number" min="0" class="form-control"
						name="tariff" id="tariff" value="${sessionScope.tariff}" required>
						<c:remove var="tariff" /></td>
				</tr>
				<tr>

					<td><input type="submit" class="btn btn-outline-dark"
						value="OK"></td>
				</tr>
			</table>

		</form>
	</div>

	<div class="d-grid col-2 py-5 mx-auto ">
		<a class="btn btn-outline-dark"
			href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
				key="add-subscriber.back" /></a>
	</div>

	<jsp:include page="components/footer.jsp" />
</body>
</html>