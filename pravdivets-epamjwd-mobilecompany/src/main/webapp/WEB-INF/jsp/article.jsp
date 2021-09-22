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
	<div class="container row align-content-center col-sm-7 mx-auto flex-grow-1">
			<h4>${requestScope.article.title}</h4><br/><br/>
			<em> ${requestScope.article.date}</em>
			<p>${requestScope.article.text}</p>
		</div>

	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>