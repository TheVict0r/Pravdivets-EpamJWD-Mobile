<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">
	<jsp:include page="components/header.jsp" />

	<div>
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>
	<div class="container row align-content-center col-sm-7 mx-auto flex-grow-1 gap-4">
		<span class="display-5 text-left ">${requestScope.service.name}</span>
		<span class="display-7">${requestScope.service.description}</span>
		<span class="fs-5"><b>Стоимость предоставления услуги — ${service.tarif/100} руб.</b></span> 
	</div>
	<br/>
	<jsp:include page="components/footer.jsp" />
</body>
</html>