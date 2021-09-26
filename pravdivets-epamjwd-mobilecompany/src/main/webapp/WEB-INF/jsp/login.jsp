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


	<div class="row align-content-center mx-auto flex-grow-1">
		<h3>Вход</h3>
			<form method="POST" action="controller?command=authentication">
				<table>
					<tr>
						<td>Phone number:</td>
						<td><input type="text" name="phone" required></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="text" name="password" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Вход"></td>
					</tr>
				</table>
			</form>
		</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>