<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale
	value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}" />
<fmt:setBundle basename="language" />


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

	<jsp:include page="components/header.jsp" />
	
	<div class="row justify-content-center display-5 fw-light mx-auto py-3 mb-3">
		<fmt:message key="add.subscriber.new.subscriber" />
	</div>
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="post" action=controller?command=add_subscriber>
			<table>
				<c:choose>
					<c:when test="${requestScope.new_subscriber eq 'true'}">
						<tr>
							<td><fmt:message key="add.subscriber.passport" />:</td>
							<td>${requestScope.passport}</td>
						</tr>

						<tr>
							<td><label for="passport" class="form-label"><fmt:message
										key="add.subscriber.passport" />:</label></td>
							<td><input type="text" class="form-control" name="passport"
								id="passport" pattern="^[A-Z]{2}[0-9]{7}$"
								value="${requestScope.passport}" required></td>
						</tr>
						<tr>
							<td><label for="last_name" class="form-label"><fmt:message
										key="add.subscriber.last.name" />:</label></td>
							<td><input type="text" class="form-control" name="last_name"
								id="last_name" pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required></td>
						</tr>
						<tr>
							<td><label for="first_name" class="form-label"><fmt:message
										key="add.subscriber.first.name" />:</label></td>
							<td><input type="text" class="form-control"
								name="first_name" id="first_name"
								pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required></td>
						</tr>
						<tr>
							<td><label for="middle_name" class="form-label"><fmt:message
										key="add.subscriber.middle.name" />:</label></td>
							<td><input type="text" class="form-control"
								name="middle_name" id="middle_name"
								pattern="^[A-ZА-ЯЁ][a-zа-яё]+$"></td>
						</tr>
						<tr>
							<td><label for="home_address" class="form-label"><fmt:message
										key="add.subscriber.address" />:</label></td>
							<td><input type="text" class="form-control"
								name="home_address" id="home_address" required></td>
						</tr>
						<tr>
							<td><label for="email" class="form-label">e-mail:</label></td>
							<td><input type="email" class="form-control" name="email"
								id="email" required></td>
						</tr>
					</c:when>
					<c:otherwise>
						<table class="table">
							<tr>
								<td><fmt:message key="add.subscriber.passport" />:</td>
								<td>${requestScope.subscriber.passportNumber}</td>
							</tr>
							<tr>
								<td><fmt:message key="add.subscriber.last.name" />:</td>
								<td>${requestScope.subscriber.lastName}</td>
							</tr>
							<tr>
								<td><fmt:message key="add.subscriber.first.name" />:</td>
								<td>${requestScope.subscriber.firstName}</td>
							</tr>
							<tr>
								<td><fmt:message key="add.subscriber.middle.name" />:</td>
								<td>${requestScope.subscriber.middleName}</td>
							</tr>
							<tr>
								<td><fmt:message key="add.subscriber.address" />:</td>
								<td>${requestScope.subscriber.homeAddress}</td>
							</tr>
							<tr>
								<td>e-mail:</td>
								<td>${requestScope.subscriber.email}</td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>

				<tr>
					<td><label for="phone_number" class="form-label"><fmt:message
								key="add.subscriber.phone.number" />:</label></td>
					<td><input type="text" class="form-control"
						name="phone_number" id="phone_number"
						pattern="^(25|29|33|44)[0-9]{7}$" required></td>
				</tr>
				<tr>
					<td><label for="plan" class="form-label"><fmt:message
								key="add.subscriber.plan" />:</label></td>
					<td><input type="text" class="form-control mb-2" name="plan"
						id="plan" required></td>
				</tr>

			</table>
			<div class="container py-3 mb-2">
				<div class="row justify-content-between">
					<div class="col-4">
						<a class="btn btn-outline-dark"
							href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_base"><fmt:message
								key="add.subscriber.back" /></a>
					</div>

					<div class="col-4">
						<input type="submit" class="btn btn-outline-dark"
							value="<fmt:message key="subscriberbase.service.next"/>">
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>