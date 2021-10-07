<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico"/>
<title>mobile</title>
</head>

<body class="d-flex flex-column min-vh-100 bg-light">
	<jsp:include page="components/header.jsp" />

	<div class="col-sm" id="promo">
		<img src="pic/news.jpg" class="img-fluid" alt="mobile">
	</div>

	

	<div class="container text-dark mx-auto col-sm-8">
	<br/>
		<h1>Новости</h1>
		<br/>
	
		<c:forEach var="article" items="${requestScope.news}">
				<h5>${article.title}</h5>
				<em> ${article.date}</em> <br />
				<p>
					${article.lead} <br /> <a
						href="${pageContext.request.contextPath}/controller?command=full_article&id=${article.id}"
						class="card-link">Подробнее</a>
				</p>
			
			<br />
		</c:forEach>

	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>