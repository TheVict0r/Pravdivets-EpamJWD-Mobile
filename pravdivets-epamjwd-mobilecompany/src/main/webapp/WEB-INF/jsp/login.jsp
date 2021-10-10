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
	<div class="row align-content-center mx-auto flex-grow-1">
		<h3>Вход</h3>
		<form method="POST" action="controller?command=login">
			<table>
				
				<c:if test="${sessionScope.error eq 'login_error'}">
					<p class="text-danger">Проверьте корректность введенных данных</p>
					<c:remove var="error" />
				</c:if>   
				<tr>
					<td>Номер телефона<span class="text-primary fw-bold fs-5 ">*</span> / e-mail:
					</td>
					<td><input type="text" class="form-control" name="login"
						value="${sessionScope.login}" required></td>
						<c:remove var="login" />
				</tr>
				<tr>
					<td>Пароль:</td>
					<td><input  type="password" class="form-control" name="password" 
						value="${sessionScope.password}" id="password" required></td>
						<c:remove var="password"/>
						
				</tr>
				<tr>
					<td></td>
					<td class="fw-light"><input class="form-check-input" type="checkbox" onclick="showPassword()" > Показать пароль</td>
				</tr>	
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark" value="Вход"></td>
				</tr>
			</table>
			<div>
			</div>
		</form>
	</div>
	<div class="row align-content-center mx-auto flex-grow-1">
		<p>
			<span class="text-primary fw-bold fs-5 ">*</span> Введите 9 цифр номера телефона в
			формате <br/><b><i>25xxxxxxx, 29xxxxxxx, 33xxxxxxx</i></b> или <b><i>44xxxxxxx</i></b>
		</p>
	</div>
	
	<jsp:include page="components/footer.jsp" />
	
	<script src="js/login.js"></script>
</body>
</html>