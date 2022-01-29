<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">
	<jsp:include page="components/header.jsp" />

	<div>
		<img src="pic/error.jpg" class="img-fluid" alt="mobile">
	</div>


	<div
		class="container row align-content-center col-sm-11 mx-auto flex-grow-1 gap-2">

		<span
			class="display-2 text-left text-danger d-flex align-items-center">
			404 Not Found </span>
<c:choose>
		<c:when test="${sessionScope.error eq 'no_article'}">
			<span class="fw-normal fs-2 text-danger"> <fmt:message
					key="article-operations.no-article" /> <b>(ID -
					${sessionScope.article_id})</b>
			</span>
		<c:remove var="article_id" />
		</c:when>
				<c:when test="${sessionScope.error eq 'no_plan'}">
			<span class="fw-normal fs-2 text-danger"> <fmt:message
					key="error.no-plan" /> <b>(ID -	${sessionScope.plan_id})</b>
			</span>
		<c:remove var="plan_id" />
		</c:when>
		
		<c:when test="${sessionScope.error eq 'no_service'}">
			<span class="fw-normal fs-2 text-danger"> <fmt:message
					key="error.no-service" /> <b>(ID - ${sessionScope.service_id})</b>
			</span>
		<c:remove var="service_id" />
		</c:when>
		
		
</c:choose>
		<c:remove var="error" />
		<span class="fs-5 text-left d-flex align-items-center">Something
			went wrong, sorry :(</span> <span
			class="fs-5 text-left d-flex align-items-center">Никогда
			такого не было, и вот опять!</span>


	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>