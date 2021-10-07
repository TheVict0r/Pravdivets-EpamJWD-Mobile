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

	<div class="align-content-center mx-auto flex-grow-1">
		<span class="fs-5 fw-bold text-left">Оператор:
			${requestScope.admin.firstName} ${requestScope.admin.lastName}</span>
	</div>


	<jsp:include page="components/footer.jsp" />
</body>
</html>