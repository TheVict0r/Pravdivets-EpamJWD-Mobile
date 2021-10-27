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
	<div class="container text-center mx-auto flex-grow-1">
		<span class="display-5 fw-light"><fmt:message key="subscriber.title"/></span>
	</div>
		<div class="container col-sm-12 col-md-10 col-lg-9 col-xl-7 fs-6 fw-light flex-grow-1">
			<table class="table ">
				<tr>
					<td><fmt:message key="subscriber.phone"/></td>
					<td>${requestScope.phone_format}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#"><fmt:message key="subscriber.edit"/></a></td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.name"/></td>
					<td>${requestScope.user.firstName} ${requestScope.user.middleName} ${requestScope.user.lastName}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#"><fmt:message key="subscriber.edit"/></a></td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.passport"/></td>
					<td>${requestScope.user.passport}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#"><fmt:message key="subscriber.edit"/></a></td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.plan"/></td>
					<td>${requestScope.plan.name}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#"><fmt:message key="subscriber.edit"/></a></td>
				</tr>
				<tr>
					<td>e-mail:</td>
					<td>${requestScope.user.email}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#"><fmt:message key="subscriber.edit"/></a></td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.contract-date"/></td>
					<td>${requestScope.subscriber.contractDate}</td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.account-number"/></td>
					<td>${requestScope.subscriber.id}</td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.account-amount"/></td>
					<td>${requestScope.subscriber.account/100} руб.</td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.status"/></td>
					<td>${requestScope.subscriber.status.statusName}</td>
				</tr>
				<tr>
					<td><fmt:message key="subscriber.status-date"/></td>
					<td>${requestScope.subscriber.statusDate}</td>
				</tr>
			</table>
		</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>