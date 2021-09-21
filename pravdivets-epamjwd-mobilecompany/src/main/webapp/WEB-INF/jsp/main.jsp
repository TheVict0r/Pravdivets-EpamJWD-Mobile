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

	<div class="container bg-light text-dark">

		<div class="container text-center">
			<h3>MAIN.JSP</h3>
			<h1>Наши новости</h1>
			<br>
		</div>

		<div class="row">
			<c:forEach var="article" items="${news}" begin="0" end="2">
				<div class="col-sm-4">
					<h4>${article.title}</h4>
					<strong><em> ${article.date}</em></strong> <br />
					<p>
						${article.lead} <br /> <a
							href="${pageContext.request.contextPath}/controller?command=full_article&id=${article.id}&path=article"
							class="card-link">Подробнее</a>
					</p>
					<br />
				</div>
			</c:forEach>
		</div>


		<form class=" d-grid gap-2 col-4 mx-auto" action="controller" method="GET">
			<input type="hidden" name="news" value="provide_news" /> <br /> 
			<input type="submit" value="Все новости" name="read"
				class="btn btn-outline-primary " />
				
		</form>

		<br/>


	</div>


	<jsp:include page="components/footer.jsp" />
	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>