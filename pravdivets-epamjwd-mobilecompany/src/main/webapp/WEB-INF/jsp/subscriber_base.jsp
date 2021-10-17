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

	<div
		class="container col-sm-12 col-md-10 col-lg-9 col-xl-9 fw-light py-4 flex-grow-1">

		<div class="display-4 text-start mb-5 flex-grow-1">
			<fmt:message key="subscriberbase.service.title" />
		</div>
		<div>
			<h4 class="mb-2">
				<fmt:message key="subscriberbase.service.find.subscriber" />
			</h4>
		</div>

		<form class="row mb-3" method="POST"
			action="controller?command=show_subscriber_by_phone">
			<label for="phoneNumber" class="form-label"><fmt:message
					key="subscriberbase.service.by.phone" /> <span
				class="fs-6 fst-italic">(<fmt:message
						key="subscriberbase.service.nine.digits" /> <b>25xxxxxxx,
						29xxxxxxx, 33xxxxxxx, 44xxxxxxx</b>)
			</span></label>
			<div class="col-md-3">
				<input type="text" class="form-control" id="phoneNumber"
					name="phone_number" value="${requestScope.phone_number}"
					placeholder="291234567" pattern="^(25|29|33|44)[0-9]{7}$" required>
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>

		<div class="mb-5">
		<form class="row mb-2" method="POST"
			action="controller?command=show_subscriber_list_by_full_name">
			<label for="lastName" class="form-label"><fmt:message
					key="subscriberbase.service.by.name" /></label>
			<div class="col-md-3">
				<input type="text" class="form-control" id="lastName"
					name="last_name" value="${requestScope.last_name}"
					placeholder="<fmt:message key="subscriberbase.service.last.name"/>"
					pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="firstName"
					name="first_name" value="${requestScope.first_name}"
					placeholder="<fmt:message key="subscriberbase.service.first.name"/>"
					pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="middleName"
					name="middle_name" value="${requestScope.middle_name}"
					placeholder="<fmt:message key="subscriberbase.service.middle.name"/>"
					pattern="^[A-ZА-ЯЁ][a-zа-яё]+$">
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>
		
				<div class="col-md-12">
			<c:if test="${requestScope.error eq 'error_null_subscriber'}">
				<span class="fw-normal fs-6 text-danger"> <fmt:message
						key="subscriberbase.service.null.subscriber" /> <c:remove
						var="error" />
				</span>
			</c:if>
		</div>
		
		
		</div>
		<div>
			<h4 class="mb-2">
				<fmt:message key="subscriberbase.service.add.subscriber" />
			</h4>
		</div>
		<div>
		<form class="row mb-2" method="POST"
			action="controller?command=check_subscriber_by_passport">
			<label for="passport" class="form-label"><fmt:message
					key="subscriberbase.service.passport" /></label>
			<div class="col-md-3">
				<input type="text" class="form-control" name="passport"
					id="passport"
					placeholder="AB1234567"
					pattern="^[A-Z]{2}[0-9]{7}$" value="${requestScope.subscriber_list[0].passportNumber}" required>
			</div>
			<div class="col-md-2">
				<input type="submit" class="btn btn-outline-dark"
					value="<fmt:message key="subscriberbase.service.next"/>">
			</div>
		</form>
		<c:if test="${requestScope.subscriber eq 'debtor'}">
			<div class="col-md-12 fw-normal fs-6 text-danger">
				<fmt:message key="subscriberbase.service.debt.found"/><c:forEach var="subscriber"
						items="${requestScope.subscriber_list}">
		 <b>${subscriber.checkingAccountAmount/100} <fmt:message key="subscriberbase.service.rub"/></b>
		 (<fmt:message key="subscriberbase.service.phone"/> ${subscriber.phoneNumber})
		</c:forEach> <br/><fmt:message key="subscriberbase.service.pay.debt"/>
			</div>
		</c:if>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>