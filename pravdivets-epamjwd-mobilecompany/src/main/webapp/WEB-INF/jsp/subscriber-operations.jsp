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
		class="container col-sm-12 col-md-10 col-lg-8 col-xl-7 fw-light py-1 flex-grow-1">

		<div class="display-4 text-start mb-3 flex-grow-1">
			<fmt:message key="subscriberbase.title" />
		</div>
		<div>
			<h4 class="mb-2">
				<fmt:message key="subscriberbase.find-subscriber" />
			</h4>
		</div>
		<form class="row mb-3" method="POST"
			action="controller?command=find_subscriber_by_phone">
			<label for="phone" class="form-label"><fmt:message
					key="subscriberbase.by-phone" /> <span
				class="fs-6 fst-italic">(<fmt:message
						key="subscriberbase.nine-digits" /> <b>55xxxxxxx, 25xxxxxxx,
						29xxxxxxx, 33xxxxxxx, 44xxxxxxx</b>)
			</span></label>
			<div class="col-md-3">
				<input type="text" class="form-control" id="phone"
					name="phone" value="${requestScope.phone}"
					placeholder="551234567" pattern="^(25|29|33|44|55)[0-9]{7}$" required>
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>
				<div class="col-md-12 mb-2">
			<c:if test="${requestScope.error eq 'wrong_phone'}">
				<span class="fw-normal fs-6 text-danger"> <fmt:message
						key="subscriberbase.null-subscriber" /> <c:remove
						var="error" />
				</span>
			</c:if>
		</div>
		<form class="row mb-3" method="POST"
			action="controller?command=find_subscriber_list_by_passport">
			<label for="passport" class="form-label"><fmt:message
					key="subscriberbase.by-passport" /></label>
			<div class="col-md-3">
				<input type="text" class="form-control" name="passport"
					id="passport"
					placeholder="AB1234567"
					pattern="^[A-Z]{2}[0-9]{7}$" value="${requestScope.passport}" required>
			</div>
			<div class="col-md-2">
				<input type="submit" class="btn btn-outline-dark"
					value="OK">
			</div>
		</form>

				<div class="col-md-12 mb-2">
			<c:if test="${requestScope.error eq 'wrong_passport'}">
				<span class="fw-normal fs-6 text-danger"> <fmt:message
						key="subscriberbase.null-subscriber" /> <c:remove
						var="error" />
				</span>
			</c:if>
		</div>
		<div class="mb-4">
		<form class="row mb-2" method="POST"
			action="controller?command=find_subscriber_list_by_full_name">
			<label for="lastName" class="form-label"><fmt:message
					key="subscriberbase.by-name" /></label>
			<div class="col-md-3">
				<input type="text" class="form-control" id="lastName"
					name="last_name" value="${requestScope.last_name}"
					placeholder="<fmt:message key="subscriberbase.last-name"/>"
					pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="firstName"
					name="first_name" value="${requestScope.first_name}"
					placeholder="<fmt:message key="subscriberbase.first-name"/>"
					pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="middleName"
					name="middle_name" value="${requestScope.middle_name}"
					placeholder="<fmt:message key="subscriberbase.middle-name"/>"
					pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$">
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>
				<div class="col-md-12">
			<c:if test="${requestScope.error eq 'wrong_name'}">
				<span class="fw-normal fs-6 text-danger"> <fmt:message
						key="subscriberbase.null-subscriber" /> <c:remove
						var="error" />
				</span>
			</c:if>
		</div>
		</div>
		<div>
			<h4 class="mb-1">
				<fmt:message key="subscriberbase.add-subscriber" />
			</h4>
		</div>
		<div>
			<form class="row" method="POST"
				action="controller?command=add_subscriber_preparation">
				<label for="passport" class="form-label"><fmt:message
						key="subscriberbase.passport" /></label>
				<div class="col-md-3">
					<input type="text" class="form-control" name="passport"
						id="passport" placeholder="AB1234567" pattern="^[A-Z]{2}[0-9]{7}$"
						value="${sessionScope.passport}" required>
				</div>
				<div class="col-md-1">
					<input type="submit" class="btn btn-outline-dark"
						value="<fmt:message key="subscriberbase.next"/>">
				</div>
			</form>
			<c:if test="${sessionScope.subscriber_debtor eq 'debtor'}">
				<div class="col-md-12 fw-normal fs-6 text-danger">
					<fmt:message key="subscriberbase.debt-found" />
					<c:forEach var="subscriber" items="${sessionScope.subscriber_list}">
						<b> <fmt:formatNumber type="number" minFractionDigits="2"
								value="${subscriber.account/100}" /> <fmt:message
								key="subscriberbase.rub" /></b>
		 (<fmt:message key="subscriberbase.phone" /> ${subscriber.phone})
		</c:forEach>
					<br />
					<fmt:message key="subscriberbase.pay-debt" />
				</div>
			</c:if>
			<c:remove var="subscriber_debtor" />
			<c:remove var="subscriber_list" />
		</div>
		<c:if test="${sessionScope.role eq 'ADMIN'}">
	<div class="d-grid col-6 py-4 mx-start ">
		<a class="btn btn-primary"
			href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
				key="admin.to-admin" /></a>
	</div>
	</c:if>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>