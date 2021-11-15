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
		class="row justify-content-center display-4 fw-light mx-auto  mb-1">
		<fmt:message key="add-subscriber.new-subscriber" />
	</div>
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="post" action=controller?command=add_subscriber>
			<div class="row justify-content-center fs-6 ">
				<fmt:message key="add-subscriber.phone-number" />:
			</div>
			<div class="row justify-content-center text-primary fs-3 mb-1">
				${sessionScope.phone_format}</div>
			<div class="row justify-content-center mb-1">
				<a class="btn btn-outline-dark"
					href="${pageContext.request.contextPath}/controller?command=add_subscriber_preparation&passport=${sessionScope.passport}"><fmt:message
						key="add-subscriber.reload" /></a>
			</div>
			<div class="row justify-content-center fs-2">
				<fmt:message key="add-subscriber.passport" />
				: ${sessionScope.passport}
			</div>
			<c:choose>
				<c:when test="${sessionScope.subscriber_user_flag eq 'new'}">

					<table>
						<tr>
							<td><label for="subscriber_user_last_name" class="form-label"><fmt:message
										key="add-subscriber.last-name" />:</label></td>
							<td><input type="text" class="form-control" name="subscriber_user_last_name"
								id="subscriber_user_last_name" pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" value="${sessionScope.subscriber_user_last_name}" required>
								<c:remove var="subscriber_user_last_name" />
								</td>
						</tr>
						<tr>
							<td><label for="subscriber_user_first_name" class="form-label"><fmt:message
										key="add-subscriber.first-name" />:</label></td>
							<td><input type="text" class="form-control"
								name="subscriber_user_first_name" id="subscriber_user_first_name"
								pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" value="${sessionScope.subscriber_user_first_name}" required>
								<c:remove var="subscriber_user_first_name" />
								</td>
						</tr>
						<tr>
							<td><label for="subscriber_user_middle_name" class="form-label"><fmt:message
										key="add-subscriber.middle-name" />:</label></td>
							<td><input type="text" class="form-control"
								name="subscriber_user_middle_name" id="subscriber_user_middle_name"
								pattern="^[A-ZА-ЯЁ][a-zа-яё]+$" value="${sessionScope.subscriber_user_middle_name}">
								<c:remove var="subscriber_user_middle_name" />
								</td>
						</tr>
						<tr>
							<td><label for="email" class="form-label">e-mail:</label></td>
							<td><input type="email" class="form-control" name="email"
								id="email" value="${sessionScope.email}" required>
								<c:remove var="email" />
								</td>
						</tr>
					</table>
					<c:if test="${sessionScope.error eq 'booked_email'}">
						<p class="row justify-content-center mx-auto text-success text-danger fw-normal">
							<fmt:message key="add-subscriber.booked-email" />
						</p>
						<c:remove var="error" />
					</c:if>
				</c:when>
				<c:otherwise>
					<div
						class="row justify-content-center mx-auto text-success text-center fw-normal">
						<fmt:message key="add-subscriber.current" />
					</div>
					<table class="table">
						<tr>
							<td><fmt:message key="add-subscriber.last-name" />:</td>
							<td>${sessionScope.subscriber_user.lastName}</td>
						</tr>
						<tr>
							<td><fmt:message key="add-subscriber.first-name" />:</td>
							<td>${sessionScope.subscriber_user.firstName}</td>
						</tr>
						<tr>
							<td><fmt:message key="add-subscriber.middle-name" />:</td>
							<td>${sessionScope.subscriber_user.middleName}</td>
						</tr>
						<tr>
							<td>e-mail:</td>
							<td>${sessionScope.subscriber_user.email}</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			
			<div class="row justify-content-center mb-3">
				<fmt:message key="add-subscriber.plan" />:
				<select class="form-select form-select-sm" id="plan" name="plan_id">
				<c:forEach var="plan" items="${sessionScope.all_plans}">
					<option value="${plan.id}">${plan.name}</option>
				</c:forEach>
				</select>
			</div>
			<div class="container py-3 mb-1">
				<div class="row justify-content-between">
					<div class="col-4">
						<a class="btn btn-outline-dark"
							href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_operations_page"><fmt:message
								key="add-subscriber.back" /></a>
					</div>
					<div class="col-4">
						<input type="submit" class="btn btn-outline-dark"
							value="<fmt:message key="subscriberbase.next"/>">
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>