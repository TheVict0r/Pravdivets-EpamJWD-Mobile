<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

		<div class="mb-4 "></div>
		<div><h1 class="row justify-content-center fw-light mx-auto mb-5 ">Новый абонент</h1></div>
		<div class="row justify-content-center mx-auto fw-light flex-grow-1">
			<form method="post" action=controller?command=add_subscriber>
				<table>
					<tr>
						<td>Фамилия:</td>
						<td><input type="text" class="form-control" name="last_name" required></td>
					</tr>
					<tr>
						<td>Имя:</td>
						<td><input type="text" class="form-control" name="first_name" required></td>
					</tr>
					<tr>
						<td>Отчество:</td>
						<td><input type="text" class="form-control" name="middle_name"></td>
					</tr>
					<tr>
						<td>Серия и номер паспорта:</td>
						<td><input type="text" class="form-control" name="passport_number" required></td>
					</tr>
					<tr>
						<td>Домашний адрес:</td>
						<td><input type="text" class="form-control" name="home_address" required></td>
					</tr>
					<tr>
						<td>e-mail:</td>
						<td><input type="text" class="form-control" name="email" required></td>
					</tr>
					<tr>
						<td>Номер телефона:</td>
						<td><input type="text" class="form-control" name="phone_number" required></td>
					</tr>
					<tr>
						<td>Тарифный план:</td>
						<td><input type="text" class="form-control mb-2" name="plan" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="btn btn-outline-dark" value="Добавить"></td>
					</tr>
				</table>
			</form>
		</div>
	
	</br>
	</br>
	<jsp:include page="components/footer.jsp"/>
</body>
</html>