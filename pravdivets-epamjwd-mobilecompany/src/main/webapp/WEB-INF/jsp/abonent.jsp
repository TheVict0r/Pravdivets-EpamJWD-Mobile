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
			</div>
			
		<div class="container col-sm-12 col-md-9 col-lg-8 col-xl-7 fs-5 fw-light flex-grow-1">
			<table class="table ">
				<tr>
					<td>ФИО:</td>
					<td>${requestScope.abonent.firstName} ${requestScope.abonent.middleName} ${requestScope.abonent.lastName}</td>
				</tr>
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
					<td><a class="login btn btn-outline-primary btn-sm" href="#">Изменить</a></td>
				</tr>
			</table>
		</div>




	<jsp:include page="components/footer.jsp" />
</body>
</html>