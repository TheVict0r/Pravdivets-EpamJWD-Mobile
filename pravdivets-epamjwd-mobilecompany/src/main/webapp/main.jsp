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

<jsp:include page="header.jsp" />

		<div class="container bg-light text-dark">
		
			<h1>MAIN.JSP</h1>
			<h1>Наши новости</h1>
			<br>

			<h4>${news[0].title}</h4>
			<em> ${news[0].date}</em><br />
			<p>
				<em><strong> ${news[0].lead} </strong></em>
			</p>
			<p>${news[0].text}</p>
			<br/>
			
			<h4>${news[1].title}</h4>
			<em> ${news[1].date}</em><br />
			<p>
				<em><strong> ${news[1].lead} </strong></em>
			</p>
			<p>${news[1].text}</p>
			<br/>
			
			<h4>${news[2].title}</h4>
			<em> ${news[2].date}</em><br />
			<p>
				<em><strong> ${news[2].lead} </strong></em>
			</p>
			<p>${news[2].text}</p>


			<form action="controller" method="GET">
				<input type="hidden" name="news" value="provide_news" /> <br /> <input
					type="submit" value="Все новости" name="read"
					class="btn btn-outline-primary" />
			</form>

			<br/>


		</div>


<jsp:include page="footer.jsp" />
		<!-- bootstrap -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous"></script>
</body>
</html>