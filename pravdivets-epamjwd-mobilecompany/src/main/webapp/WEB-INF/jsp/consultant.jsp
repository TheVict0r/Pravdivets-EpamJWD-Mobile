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
	
	<div class="container mx-auto flex-grow-1">
		<span class="fs-5 fw-bold text-start">Оператор: ${requestScope.consultant.firstName} ${requestScope.consultant.lastName}</span>
	</div>

	<div
		class="container col-sm-9 align-content-center fs-5 fw-light flex-grow-1">
		<form method="POST" action="controller?command=abonent">
			<p>Показать данные абонента по номеру телефона</p>
			<input type="text" name="phone_number" value="" required> 
			<input type="submit" value="OK">
		</form>
	</div>



	<jsp:include page="components/footer.jsp" />
</body>
</html>