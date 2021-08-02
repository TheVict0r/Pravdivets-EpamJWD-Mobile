<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	
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


	<div class="container">
	
	<h1>Наши новости</h1>
	<br>
	

	<form action="controller" method="GET" >
		<input type="hidden" name="news" value="provide_news" /> <br/>
		<input	type="submit" value="Читать" name="read" class="btn btn-outline-primary"/>
		
	</form>
	
	
	
	<br />

	<br>
		<c:out value = "Тест JSTL"/>
		<br>
		<c:out value="${'Welcome to javaTpoint'}" />
		
		<br>
		<br>
		
		
		
		<form action="servletURL" method="get">
	        <type="hidden" name="news" value="provide_three_news" />
	        <h4>hidden provide_three_news</h4>
		</form>
		
		</div>
		
<!-- bootstrap -->		
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>