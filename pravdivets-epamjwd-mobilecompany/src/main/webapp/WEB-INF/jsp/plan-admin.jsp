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
	<div class="container row align-content-center col-sm-7 mx-auto flex-grow-1 ">
		<span class="display-6 text-center py-4"><fmt:message key="plan.plan"/></span> 
		<span class="display-4 text-center">${sessionScope.plan.name}</span> 
		<p class="lead text-center mb-1">${plan.description}</p>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th><fmt:message key="plan.feature"/></th>
					<th><fmt:message key="plan.price"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><fmt:message key="plan.regular-payment"/></td>
					<td>${sessionScope.plan.regularPayment/100} <fmt:message key="plan.rub-month"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.upfront-payment"/></td>
					<td>${sessionScope.plan.upfrontPayment/100} <fmt:message key="plan.rub"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-within-network"/></td>
					<td>${sessionScope.plan.priceWithinNetwork/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-other-networks"/></td>
					<td>${sessionScope.plan.priceOtherNetworks/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-abroad"/></td>
					<td>${sessionScope.plan.priceAbroad/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-videocalls"/></td>
					<td>${sessionScope.plan.priceVideocall/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td>SMS</td>
					<td>${sessionScope.plan.priceSMS/100} <fmt:message key="plan.rub-unit"/></td>
				</tr>
				<tr>
					<td>MMS</td>
					<td>${sessionScope.plan.priceMMS/100} <fmt:message key="plan.rub-unit"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-internet"/></td>
					<td>${sessionScope.plan.priceInternet/100} <fmt:message key="plan.rub-mb"/></td>
				</tr>
			</tbody>
		</table>
	
		<div class="d-grid col-6 py-2 mx-start ">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
					key="admin.to-admin" /></a>
		</div>
		<div class="d-grid col-6 py-2 mx-start ">
			<a class="btn btn-outline-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_plan_operations_page">
				<fmt:message key="admin.plan-operations" /></a>
		</div>
	
	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>