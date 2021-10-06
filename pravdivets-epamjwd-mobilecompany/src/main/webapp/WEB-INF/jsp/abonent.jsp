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

		<span class="display-5 text-center">Здравствуйте,
			${requestScope.abonent.firstName} ${requestScope.abonent.middleName}!</span>
		<div class="container fs-4 fw-light ">
			Номер телефона: ${requestScope.abonent.phoneNumber}</br> 
			Лицевой счет №: ${requestScope.abonent.id}</br> 
			Баланс лицевого счета: ${requestScope.abonent.checkingAccountAmount/100} руб.</br>
			Статус: ${requestScope.abonent.status.statusName}</br>
			Тарифный план: ${requestScope.abonent.tariffPlan}</br>
		</div>


	</div>

	<jsp:include page="components/footer.jsp" />
</body>
</html>