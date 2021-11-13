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
<body class="vh-100 d-flex flex-column justify-content-center bg-light">
	<jsp:include page="components/header.jsp" />
	<div class="container text-center mx-auto flex-grow-1">
		<span class="display-5 fw-light"><fmt:message
				key="subscriber.services" /></span>
	</div>
	<div class="container col-sm-12 col-md-12 col-lg-8 col-xl-7 py-3 fs-6 flex-grow-1">
		<table class="table">
					<tr class="fw-bold ">
						<td><fmt:message key="subscriber.name" /></td>
						<td>${sessionScope.subscriber_user.firstName}
							${sessionScope.subscriber_user.middleName}
							${sessionScope.subscriber_user.lastName}</td>
							<td></td>
					</tr>
					<tr class="fw-bold">
						<td><fmt:message key="subscriber.phone" /></td>
						<td>${sessionScope.phone_format}</td>
						<td></td>
					</tr>
					
					<tr>
					<td>ТУТ УСЛУГИ</td>
					<td></td>
					</tr>
		</table>
		
		<a class="btn btn-primary btn-sm"
		href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_page">
		<fmt:message key="subscriber.back" /></a>
		
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>