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
	<div class="col-sm" id="promo">
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>
	<div
		class="container row align-content-center col-sm-7 mx-auto flex-grow-1 ">
		<span class="display-6 text-center py-4"><fmt:message
				key="plan.plan" /></span> <span class="display-3 text-center">${sessionScope.plan.name}</span>
		<p class="lead text-center mb-4">${sessionScope.plan.description}</p>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th><fmt:message key="plan.feature" /></th>
					<th><fmt:message key="plan.price" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><fmt:message key="plan.upfront-payment" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.upfrontPayment/100}" /> <fmt:message
							key="plan.rub" /></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.regular-payment" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.regularPayment/100}" /> <fmt:message
							key="plan.rub-month" /></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-within-network" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.priceWithinNetwork/100}" /> <fmt:message
							key="plan.rub-min" /></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-other-networks" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.priceOtherNetworks/100}" /> <fmt:message
							key="plan.rub-min" /></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-abroad" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.priceAbroad/100}" /> <fmt:message
							key="plan.rub-min" /></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-videocalls" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.priceVideocall/100}" /> <fmt:message
							key="plan.rub-min" /></td>
				</tr>
				<tr>
					<td>SMS</td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.priceSMS/100}" /> <fmt:message
							key="plan.rub-unit" /></td>
				</tr>
				<tr>
					<td>MMS</td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.priceMMS/100}" /> <fmt:message
							key="plan.rub-unit" /></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-internet" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2"
							value="${sessionScope.plan.priceInternet/100}" /> <fmt:message
							key="plan.rub-mb" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>