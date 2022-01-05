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

	<div class="container text-center py-3 mb-3 mx-auto ">
		<span class="display-3 fw-light"><fmt:message
				key="consultant.title" /></span>
	</div>
				<div class="row align-content-center mx-auto flex-grow-1">
					<c:if test="${sessionScope.change_password == 'true'}">
						<p class="text-success fs-5 ">
							<fmt:message key="consultant.change-password-success" />
						</p>
					</c:if>
						<c:remove var="change_password" />
				</div>
	<div
		class="container col-sm-12 col-md-12 col-lg-8 col-xl-7 fs-6 fw-light flex-grow-1">
		<c:choose>
			<c:when test="${sessionScope.mode eq 'edit'}">
				<div
					class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto text-danger fw-normal">
					<p>
						<c:choose>
							<c:when test="${sessionScope.error == 'booked_passport'}">
								<fmt:message key="add-consultant.booked-passport" />
							</c:when>
							<c:when test="${sessionScope.error == 'booked_email'}">
								<fmt:message key="add-consultant.booked-email" />
							</c:when>
						</c:choose>
						<c:remove var="error" />
					</p>
				</div>

				<form method="post" action="controller?command=edit_consultant">
					<table class="table table-borderless ">
						<tr>
							<td><label for="consultant_last_name" class="form-label"><fmt:message
										key="consultant.last-name" />:</label></td>
							<td><input type="text" class="form-control"
								name="consultant_last_name" id="consultant_last_name"
								pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
								value="${sessionScope.consultant.lastName}" required> <c:remove
									var="lastName" /></td>
							<td></td>
						<tr>
							<td><label for="consultant_first_name" class="form-label"><fmt:message
										key="consultant.first-name" />:</label></td>
							<td><input type="text" class="form-control"
								name="consultant_first_name" id="consultant_first_name"
								pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
								value="${sessionScope.consultant.firstName}" required> <c:remove
									var="firstName" /></td>
							<td></td>
						</tr>
						<tr>
							<td><label for="consultant_middle_name" class="form-label"><fmt:message
										key="consultant.middle-name" />:</label></td>
							<td><input type="text" class="form-control"
								name="consultant_middle_name" id="consultant_middle_name"
								pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
								value="${sessionScope.consultant.middleName}"> <c:remove
									var="middleName" /></td>
							<td></td>
						</tr>
						<tr>
							<td><label for="passport" class="form-label"><fmt:message
										key="consultant.passport" />:</label></td>
							<td><input type="text" class="form-control"
								name="passport" id="email"
								value="${sessionScope.consultant.passport}" required> <c:remove
									var="passport" /></td>
							<td></td>
						</tr>
						<tr>
							<td><label for="email" class="form-label">e-mail:</label></td>
							<td><input type="email" class="form-control"
								name="email" id="email" value="${sessionScope.consultant.email}"
								required> <c:remove var="email" /></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td><a class="btn btn-secondary btn-sm me-md-5"
								href="${pageContext.request.contextPath}/controller?command=cancel_edit_consultant"><fmt:message
										key="subscriber.cancel" /></a>
							
							<input type="submit" class="btn btn-primary btn-sm me-md-5"
								value="<fmt:message key="consultant.save"/>"></td>
						</tr>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<table class="table">
					<tr>
						<td><fmt:message key="consultant.last-name" /></td>
						<td>${sessionScope.consultant.lastName}</td>
					</tr>
					<tr>
						<td><fmt:message key="consultant.first-name" /></td>
						<td>${sessionScope.consultant.firstName}</td>
					</tr>
					<tr>
						<td><fmt:message key="consultant.middle-name" /></td>
						<td>${sessionScope.consultant.middleName}</td>
					</tr>
					<tr>
						<td><fmt:message key="consultant.passport" /></td>
						<td>${sessionScope.consultant.passport}</td>
					</tr>
					<tr>
						<td>e-mail:</td>
						<td>${sessionScope.consultant.email}</td>
					</tr>
				</table>
				<div class="d-grid col-5 py-1 mb-3 mx-auto ">
					<a class="btn btn-outline-dark"
						href="${pageContext.request.contextPath}/controller?command=edit_consultant_preparation"><fmt:message
							key="consultant.edit" /></a>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="d-grid col-5 py-1 mb-3 mx-auto ">
			<a class="btn btn-outline-dark"
				href="${pageContext.request.contextPath}/controller?command=go_to_change_password_page"><fmt:message
					key="consultant.change-password" /></a>
		</div>
		<div class="d-grid col-5 py-1 mb-3 mx-auto ">
			<a class="btn btn-outline-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_consultant_operations_page"><fmt:message
					key="consultant.to-consultant-operations" /></a>
		</div>
		<div class="d-grid col-5 py-1 mb-3 mx-auto ">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
					key="admin.to-admin" /></a>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>