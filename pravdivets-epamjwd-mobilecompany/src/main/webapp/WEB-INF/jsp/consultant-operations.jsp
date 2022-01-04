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

	<div
		class="container col-sm-12 col-md-10 col-lg-8 col-xl-8 fw-light py-3 flex-grow-1">

		<div class="display-5 text-start mb-4 flex-grow-1">
			<fmt:message key="consultant-operations.title" />
		</div>
		<div>
			<h4 class="mb-2">
				<fmt:message key="consultant-operations.find-consultant" />
			</h4>
		</div>
					<div class="col-md-12">
				<c:if test="${sessionScope.error eq 'error'}">
					<span class="fw-normal fs-6 text-danger"> <fmt:message
							key="consultant-operations.null-subscriber" /> <c:remove var="error" />
					</span>
				</c:if>
			</div>
		
		
		<form class="row mb-3" method="POST"
			action="controller?command=show_consultant_by_email">
			<label for="email" class="form-label"><fmt:message
					key="consultant-operations.by-email" /></label>
			<div class="col-md-3">
				<input type="email" class="form-control" name="email"
						id="email" placeholder="ivanov@mail.com"
						value="${sessionScope.email}" required> <c:remove
							var="email" />
			</div>
			<div class="col-md-2">
				<input type="submit" class="btn btn-outline-dark"
					value="OK">
			</div>
		</form>

		<form class="row mb-3" method="POST"
			action="controller?command=show_consultant_by_passport">
			<label for="passport" class="form-label"><fmt:message
					key="consultant-operations.by-passport" /></label>
			<div class="col-md-3">
				<input type="text" class="form-control" name="passport"
					id="passport"
					placeholder="AB1234567"
					pattern="^[A-Z]{2}[0-9]{7}$" value="${sessionScope.passport}" required>
					<c:remove var="passport"/>
			</div>
			<div class="col-md-2">
				<input type="submit" class="btn btn-outline-dark"
					value="OK">
			</div>
		</form>

		<div class="mb-4">
			<form class="row mb-2" method="POST"
				action="controller?command=show_consultant_by_full_name">
				<label for="lastName" class="form-label"><fmt:message
						key="consultant-operations.by-name" /></label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="lastName"
						name="last_name" value="${sessionScope.last_name}"
						placeholder="<fmt:message key="consultant-operations.last-name"/>"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$" required>
						<c:remove var="last_name"/>
				</div>
				<div class="col-md-3">
					<input type="text" class="form-control" id="firstName"
						name="first_name" value="${sessionScope.first_name}"
						placeholder="<fmt:message key="consultant-operations.first-name"/>"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$" required>
						<c:remove var="first_name"/>
				</div>
				<div class="col-md-3">
					<input type="text" class="form-control" id="middleName"
						name="middle_name" value="${sessionScope.middle_name}"
						placeholder="<fmt:message key="consultant-operations.middle-name"/>"
						pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$">
						<c:remove var="middle_name"/>
				</div>
				<div class="col-md-1">
					<input type="submit" class="btn btn-outline-dark" value="OK">
				</div>
			</form>

		</div>
		
				<div>
			<h4 class="mb-2">
				<fmt:message key="consultant-operations.add-consultant" />
			</h4>
		</div>
		
		
		
		<div class="d-grid col-5 py-2 mx-start ">
			<a class="btn btn-outline-dark"
				href="${pageContext.request.contextPath}/controller?command=go_to_add_consultant_page">
				<fmt:message key="consultant-operations.add" />
			</a>
		</div>

		<div class="d-grid col-1 py-2 mx-start ">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
					key="add-subscriber.back" /></a>
		</div>

	</div>


	<jsp:include page="components/footer.jsp" />
</body>
</html>