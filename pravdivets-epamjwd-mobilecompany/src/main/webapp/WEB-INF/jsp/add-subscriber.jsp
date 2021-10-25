<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

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
			<div class="row justify-content-center fs-2">
				<fmt:message key="add-subscriber.passport" />
				: ${requestScope.passport}
			</div>
			
			<input type = "hidden" name = "passport" value = "${requestScope.passport}"/>
			
			<c:choose>
				<c:when test="${requestScope.user eq 'new'}">
			<input type = "hidden" name = "user" value = "new"/>
					<table>
						<tr>
							<td><label for="last_name" class="form-label"><fmt:message
										key="add-subscriber.last-name" />:</label></td>
							<td><input type="text" class="form-control" name="last_name"
								id="last_name" pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required></td>
						</tr>
						<tr>
							<td><label for="first_name" class="form-label"><fmt:message
										key="add-subscriber.first-name" />:</label></td>
							<td><input type="text" class="form-control"
								name="first_name" id="first_name"
								pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required></td>
						</tr>
						<tr>
							<td><label for="middle_name" class="form-label"><fmt:message
										key="add-subscriber.middle-name" />:</label></td>
							<td><input type="text" class="form-control"
								name="middle_name" id="middle_name"
								pattern="^[A-ZА-ЯЁ][a-zа-яё]+$"></td>
						</tr>
						<tr>
							<td><label for="email" class="form-label">e-mail:</label></td>
							<td><input type="email" class="form-control" name="email"
								id="email" required></td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
			<input type = "hidden" name = "user" value = "current"/>
					<div
						class="row justify-content-center mx-auto text-success text-center fw-normal">
						<fmt:message key="add-subscriber.current" />
					</div>
					<table class="table">
						<tr>
							<td><fmt:message key="add-subscriber.last-name" />:</td>
							<td>${requestScope.user.lastName}</td>
						</tr>
						<tr>
							<td><fmt:message key="add-subscriber.first-name" />:</td>
							<td>${requestScope.user.firstName}</td>
						</tr>
						<tr>
							<td><fmt:message key="add-subscriber.middle-name" />:</td>
							<td>${requestScope.user.middleName}</td>
						</tr>
						<tr>
							<td>e-mail:</td>
							<td>${requestScope.user.email}</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			<div class="row justify-content-center fs-6 ">
				<fmt:message key="add-subscriber.phone-number" />:
			</div>
			<div class="row justify-content-center text-primary fs-3 mb-1">
				${requestScope.phone_format}</div>
			<div class="row justify-content-center mb-1">
				<a class="btn btn-outline-dark"
					href="${pageContext.request.contextPath}/controller?command=prepare_new_subscriber&passport=${requestScope.passport}"><fmt:message
						key="add-subscriber.reload" /></a>
			</div>
			<input type = "hidden" name = "phone_number" value = "${requestScope.phone}"/>
			
			<div class="row justify-content-center mb-3">
				<fmt:message key="add-subscriber.plan" />:
				<select class="form-select form-select-sm" id="plan" name="plan_id">
				<c:forEach var="plan" items="${requestScope.all_plans}">
					<option value="${plan.id}">${plan.name}</option>
				</c:forEach>
				</select>
			</div>
			<div class="container py-3 mb-1">
				<div class="row justify-content-between">
					<div class="col-4">
						<a class="btn btn-outline-dark"
							href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_base"><fmt:message
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