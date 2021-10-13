<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}" />
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

	<div class="container col-sm-12 col-md-10 col-lg-9 col-xl-9  fs-5 fw-light flex-grow-1">
		<h5 class="mt-4">Показать данные абонента</h5>
		<form class="row" method="POST" action="controller?command=show_abonent_by_phone">
				<div >
					по номеру телефона
				</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="phoneNumber" name="phone_number" value="${requestScope.phone_number}" placeholder="Номер телефона" required>
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
			<div class="col-md-7">

				<c:if test="${requestScope.error eq 'error_null_abonent'}">
					<p class="text-danger">
						<fmt:message key="consultant.check.data" />
						<c:remove var="error" />
					</p>
					<c:remove var="error" />
				</c:if>



			</div>
		</form>

		<form class="row" method="POST" action="controller?command=show_customer_by_name">
				<div >
					по ФИО
				</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="lastName" name="last_name" value=""
					placeholder="Фамилия" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" name="first_name" value=""
					placeholder="Имя" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" name="middle_name" value=""
					placeholder="Отчество" required>
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>
				<h5 class="mt-4">Добавить нового абонента</h5>
		
		
	</div>




	<jsp:include page="components/footer.jsp" />
</body>
</html>