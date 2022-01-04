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

	<div class="container text-center py-5 mb-3 mx-auto ">
		<span class="display-3 fw-light"><fmt:message
				key="consultant.title" /></span>
	</div>
	<div
		class="container col-sm-12 col-md-12 col-lg-8 col-xl-7 fs-6 fw-light flex-grow-1">
		<c:choose>
			<c:when test="${sessionScope.mode eq 'edit'}">
			<form method="post" action="controller?command=edit_consultant">
			<table class="table table-borderless">
			<tr>
						<td><label for="consultant_last_name" class="form-label"><fmt:message
									key="consultant.last-name" />:</label></td>
						<td colspan="2"><input type="text" class="form-control"
							name="consultant_last_name" id="consultant_last_name"
							pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
							value="${sessionScope.consultant.lastName}" required>
						</td>
						<td></td>
					<tr>
						<td><label for="consultant_first_name"
							class="form-label"><fmt:message
									key="consultant.first-name" />:</label></td>
						<td colspan="2"><input type="text" class="form-control"
							name="consultant_first_name" id="consultant_first_name"
							pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
							value="${sessionScope.consultant.firstName}" required>
						</td>
						<td></td>
					</tr>
					<tr>
						<td><label for="consultant_middle_name"
							class="form-label"><fmt:message
									key="consultant.middle-name" />:</label></td>
						<td colspan="2"><input type="text" class="form-control"
							name="consultant_middle_name" id="consultant_middle_name" 
							pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
							value="${sessionScope.consultant.middleName}"></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="passport" class="form-label"><fmt:message
									key="consultant.passport" />:</label></td>
						<td colspan="2"><input type="text" class="form-control" name="passport"
							id="email" value="${sessionScope.consultant.passport}"
							required></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="email" class="form-label">e-mail:</label></td>
						<td colspan="2"><input type="email" class="form-control"  name="email"
							id="email" value="${sessionScope.consultant.email}" required>
						</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><a class="btn btn-secondary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=cancel_edit_consultant"><fmt:message
									key="subscriber.cancel" /></a></td>
						<td>
						<input type="submit" class="btn btn-primary btn-sm"
							value="<fmt:message key="consultant.edit"/>">
						</td>
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
		<div class="d-grid col-4 py-1 mb-3 mx-auto ">
			<a class="btn btn-outline-dark"
				href="${pageContext.request.contextPath}/controller?command=edit_consultant_preparation"><fmt:message
					key="consultant.edit" /></a>
		</div>
				
			</c:otherwise>
		</c:choose>
		<div class="d-grid col-4 py-1 mb-3 mx-auto ">
			<a class="btn btn-outline-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
					key="consultant.back" /></a>
		</div>

	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>