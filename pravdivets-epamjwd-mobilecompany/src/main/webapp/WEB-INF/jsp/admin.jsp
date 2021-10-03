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
<body>

	<jsp:include page="components/header.jsp" />

	<h1>Admin.jsp</h1>

	<span class="display-3 text-center">${requestScope.user.firstName}</span>



	<jsp:include page="components/footer.jsp" />
</body>
</html>