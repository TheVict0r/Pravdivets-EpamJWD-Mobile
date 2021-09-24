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

	<div class="col-sm" id="promo">
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>

	

	<div class="container text-dark mx-auto col-sm-8">
	<br/>
		<h1>ALL TAFIFF PLANS WILL BE HERE</h1>
		<br/>
	
	${requestScope.all_tariffs}

	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>