<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">


<title>mobile</title>
</head>
<body>

	<jsp:include page="components/header.jsp" />


	<div class="container">
		<div class="row justify-content-center">
			<h3>Новый абонент</h3>
			<form method="post" action=/controller?command=add-abonent>
				<table>
					<tr>
						<td>Фамилия:</td>
						<td><input type="text" name="last-name" required></td>
					</tr>
					<tr>
						<td>Имя:</td>
						<td><input type="text" name="first-name" required></td>
					</tr>
					<tr>
						<td>Отчество:</td>
						<td><input type="text" name="middle-name" required></td>
					</tr>
					<tr>
						<td>Серия и номер паспорта:</td>
						<td><input type="text" name="passport-number" required></td>
					</tr>
					<tr>
					<tr>
						<td>Домашний адрес:</td>
						<td><input type="text" name="home-address" required></td>
					</tr>
					<tr>
					<tr>
						<td>e-mail:</td>
						<td><input type="text" name="email" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Добавить"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</br>
	</br>
	<jsp:include page="components/footer.jsp"/>
	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>