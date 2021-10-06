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
	<div class="row align-content-center mx-auto flex-grow-1">
		<span class="display-4 text-center">Личный кабинет</span>
		<h4 class="h4 mb-3 text-center">${requestScope.abonent.firstName} ${requestScope.abonent.middleName} ${requestScope.abonent.lastName}</h4>
			</div>
			
		<div class="container col-sm-9 align-content-center fs-5 fw-light flex-grow-1">
			<table class="table ">
				<tr>
					<td>Номер телефона:</td>
					<td>${requestScope.abonent.phoneNumber}</td>
				</tr>
				<tr>
					<td>Текущий баланс:</td>
					<td>${requestScope.abonent.checkingAccountAmount/100} руб.</td>
				</tr>
				<tr>
					<td>Статус:</td>
					<td>${requestScope.abonent.status.statusName}</td>
				</tr>
				<tr>
					<td>Лицевой счет №:</td>
					<td>${requestScope.abonent.id}</td>
				</tr>
				<tr>
					<td>Тарифный план: </td>
					<td>${requestScope.abonent.tariffPlan}</td>
					<td>Сменить тариф >></td>
				</tr>
			</table>
		</div>




	<jsp:include page="components/footer.jsp" />
</body>
</html>