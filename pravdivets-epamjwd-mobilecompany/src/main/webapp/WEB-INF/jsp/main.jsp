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
			<div class="col-sm-4">
				<h4>${news[0].title}</h4>
				<strong><em> ${news[0].date}</em></strong><br />
				<p>
					 ${news[0].lead}
					 <br/>
					<a href="${pageContext.request.contextPath}/controller?command=full_article&id=${news[0].id}&path=article" class="card-link">Подробнее</a>
					
				</p>
				
				<br />
			</div>

			<div class="col-sm-4">
				<h4>${news[1].title}</h4>
				<strong><em> ${news[1].date}</em></strong><br />
				<p>
					 ${news[1].lead}
					 <br/>
					<a href="${pageContext.request.contextPath}/controller?command=full_article&id=${news[1].id}&path=article" class="card-link">Подробнее</a>
				</p>
				<br />
			</div>
			<div class="col-sm-4">
				<h4>${news[2].title}</h4>
				<strong><em> ${news[2].date}</em></strong><br />
				<p>
					 ${news[2].lead}
					 <br/>
					<a href="${pageContext.request.contextPath}/controller?command=full_article&id=${news[2].id}&path=article" class="card-link">Подробнее</a>
				</p>
			</div>
			<div class="col-sm-4">
				<h4>${news[3].title}</h4>
				<strong><em> ${news[3].date}</em></strong><br />
				<p>
					 ${news[3].lead}
					 <br/>
					<a href="${pageContext.request.contextPath}/controller?command=full_article&id=${news[3].id}&path=article" class="card-link">Подробнее</a>
				</p>
				</div>
			<div class="col-sm-4">
				<h4>${news[4].title}</h4>
				<strong><em> ${news[4].date}</em></strong><br />
				<p>
					 ${news[4].lead}
					 <br/>
					<a href="${pageContext.request.contextPath}/controller?command=full_article&id=${news[4].id}&path=article" class="card-link">Подробнее</a>
				</p>
				</div>
			<div class="col-sm-4">
				<h4>${news[5].title}</h4>
				<strong><em> ${news[5].date}</em></strong><br />
				<p>
					 ${news[5].lead}
					 <br/>
					<a href="${pageContext.request.contextPath}/controller?command=full_article&id=${news[5].id}&path=article" class="card-link">Read more</a>
				</p>
				</div>
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