<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'ru'}" />
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
	<div class="col-sm" id="promo">
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>
	<div class="container row align-content-center col-sm-7 mx-auto flex-grow-1 ">
		<span class="display-6 text-center py-4"><fmt:message key="plan.plan"/></span> 
		<span class="display-3 text-center">${requestScope.plan.name}</span> 
		<p class="lead text-center mb-4">${plan.description}</p>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th><fmt:message key="plan.feature"/></th>
					<th><fmt:message key="plan.price"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><fmt:message key="plan.upfront-payment"/></td>
					<td>${plan.upfrontPayment/100} <fmt:message key="plan.rub"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.regular-payment"/></td>
					<td>${plan.regularPayment/100} <fmt:message key="plan.rub-month"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-within-network"/></td>
					<td>${plan.priceWithinNetwork/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-other-networks"/></td>
					<td>${plan.priceOtherNetworks/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-abroad"/></td>
					<td>${plan.priceAbroad/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-videocalls"/></td>
					<td>${plan.priceVideocall/100} <fmt:message key="plan.rub-min"/></td>
				</tr>
				<tr>
					<td>SMS</td>
					<td>${plan.priceSMS/100} <fmt:message key="plan.rub-unit"/></td>
				</tr>
				<tr>
					<td>MMS</td>
					<td>${plan.priceMMS/100} <fmt:message key="plan.rub-unit"/></td>
				</tr>
				<tr>
					<td><fmt:message key="plan.price-internet"/></td>
					<td>${plan.priceInternet/100} <fmt:message key="plan.rub-mb"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>