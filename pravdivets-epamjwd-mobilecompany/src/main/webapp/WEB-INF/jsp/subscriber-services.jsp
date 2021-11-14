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
<body
	class="vh-100 d-flex flex-column justify-content-center fw-light bg-light">
	<jsp:include page="components/header.jsp" />
	<div class="container text-center mx-auto flex-grow-1">
		<span class="display-4 fw-light"><fmt:message
				key="subscriber.services" /></span>
	</div>
	<div class="container text-center fs-3 fw-normal mx-auto flex-grow-1">
		${sessionScope.phone_format}</div>
	<div
		class="container text-start col-sm-12 col-md-10 col-lg-7 col-xl-6 py-2 fs-6  flex-grow-1">
		<table class="table">
			<thead>
				<tr>
					<td></td>
					<td class="fw-normal"><fmt:message
							key="subscriber.activate" /></td>
					<td></td>
				</tr>
			</thead>
			<c:forEach var="service" items="${sessionScope.all_services}">
				<tr>
					<td>${service.name}</td>
					<td class="">
						<div class="form-check form-switch justify-content-end">
							<input class="form-check-input" type="checkbox" role="switch"
								id="switchCheck">
						</div>
					</td>
					<td class="text-end"><a
						href="${pageContext.request.contextPath}/controller?command=show_full_service&id=${service.id}"
						class="card-link"> <fmt:message key="subscriber.about-service" /></a></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-primary btn-sm justify-content-start"
			href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_page">
			<fmt:message key="subscriber.back" />
		</a>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>