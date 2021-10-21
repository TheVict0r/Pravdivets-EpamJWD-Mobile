<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale
	value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}" />
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

	<div>
		<img src="pic/404.jpg" class="img-fluid" alt="mobile">
	</div>


	<div
		class="container row align-content-center col-sm-8 mx-auto flex-grow-1 gap-2">

		<span class="display-2 text-left text-danger d-flex align-items-center">- 404 -</span>
		<span class="display-6 text-left d-flex align-items-center">Something went wrong, sorry :(</span>
		<span class="display-6 text-left d-flex align-items-center">Никогда такого не было, и вот опять!</span>


	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>