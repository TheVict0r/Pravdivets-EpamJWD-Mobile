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

		<span class="display-6 text-center">Здравствуйте, ${requestScope.abonent_list[0].firstName} ${requestScope.abonent_list[0].middleName}!</span>
		</div>
	<div class="row align-content-center mx-auto flex-grow-1">

		<span class="fs-4 fw-light text-center">Пожалуйста, выберите тот номер телефона, </br>информацию по которому Вы хотите получить:</span> 
			</div>
			
			<div class="fs-4 fw-bold text-center text-primary flex-grow-1">
			<c:forEach var="abonent" items="${requestScope.abonent_list}">
			<a  href="${pageContext.request.contextPath}/controller?command=abonent&phone_number=${abonent.phoneNumber}">${abonent.phoneNumber}</a></br>
			</c:forEach>
			</div>

	<jsp:include page="components/footer.jsp" />
</body>
</html>