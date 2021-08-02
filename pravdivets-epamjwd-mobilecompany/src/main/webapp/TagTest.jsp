<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">



<title>mobile</title>

<!--style type="text/css">
body {
	background-color: #FFFAFA;
	margin-left: 10%;
	margin-right: 10%;
	padding: 30px 10px 10px 10px;
	font-family: sans-serif;
}
</style-->

</head>
<body>

	<div class="container bg-light text-dark">
	
		<h1>Наши новости</h1>
		<br>
	
		<c:forEach items="${news}" var="article">
			<h4> ${article.title} </h4>
			<em> ${article.date} </em><br/>
			<p><em><strong> ${article.lead} </strong></em></p>
			<p> ${article.text} </p>
			<br/>
		</c:forEach>
	</div>

<!-- bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
