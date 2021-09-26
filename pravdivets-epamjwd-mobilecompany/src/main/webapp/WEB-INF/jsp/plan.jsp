<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">
	<jsp:include page="components/header.jsp" />

	<div class="col-sm" id="promo">
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>


	<div
		class="container row align-content-center col-sm-7 mx-auto flex-grow-1 gap-2">

		
		<span class="display-6 text-center">Тарифный план</span> 
		<span class="display-3 text-center">${requestScope.plan.name}</span> 

		
		
		<p class="lead text-center mb-4">${plan.description}</p>
		
		
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Услуга</th>
					<th>Цена</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Абонентская плата</td>
					<td>${plan.regularPayment/100} руб.</td>
				</tr>
				<tr>
					<td>Исходящие внутри сети и на голосовую почту</td>
					<td>${plan.priceWithinNetwork/100} руб.</td>
				</tr>
				<tr>
					<td>На стационарную сеть, сети других мобильных операторов Беларуси</td>
					<td>${plan.priceOtherNetworks/100} руб. </td>
				</tr>
				<tr>
					<td>Международные звонки</td>
					<td>${plan.priceAbroad/100} руб.</td>
				</tr>
				<tr>
					<td>Видеозвонки</td>
					<td>${plan.priceVideocall/100} руб.</td>
				</tr>
				<tr>
					<td>Короткие сообщения SMS</td>
					<td>${plan.priceSMS/100} руб.</td>
				</tr>
				<tr>
					<td>Мультимедийные сообщения (MMS)</td>
					<td>${plan.priceMMS/100} руб.</td>
				</tr>
			</tbody>
		</table>


	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>