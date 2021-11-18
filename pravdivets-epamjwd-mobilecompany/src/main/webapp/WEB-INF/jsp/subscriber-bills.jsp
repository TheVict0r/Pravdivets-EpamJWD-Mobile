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

	<div class=" text-center align-items-start">
		<span class="display-5 fw-light"><fmt:message
				key="subscriber.bills" /></span>
	</div>

	<div
		class="text-center fs-3 fw-normal align-items-start">
		${sessionScope.phone_format}</div>

	<div
		class="text-center fs-3 fw-normal align-items-start">
		<fmt:message key="subscriber.account-number" />
		${sessionScope.subscriber.id}
	</div>

	<div class="text-start col-sm-12 col-md-10 col-lg-7 col-xl-6 py-2 fs-6 mx-auto align-items-start flex-grow-1">
		<table class="table table-striped">
			<thead class="fw-bold">
				<tr>
					<td><fmt:message key="subscriber.bill-number" /></td>
					<td><fmt:message key="subscriber.bill-date" /></td>
					<td><fmt:message key="subscriber.bill-sum" /></td>
				</tr>
			</thead>

			<c:forEach var="bill" items="${sessionScope.bill_list}">
				<tr>
					<td>${bill.id}</td>
					<td>${bill.date}</td>
					<td>${bill.amount/100}<fmt:message key="subscriber.rub" /></td>
				</tr>
			</c:forEach>
		</table>

		
		<a class="btn btn-primary btn-sm align-items-end flex-grow-1"
			href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_page">
			<fmt:message key="subscriber.back" />
		</a>

	</div>
	
		<jsp:include page="components/footer.jsp" />

</body>
</html>