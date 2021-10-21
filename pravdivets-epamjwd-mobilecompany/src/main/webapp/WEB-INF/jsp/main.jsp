<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale
	value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light text-dark">
	<jsp:include page="components/header.jsp" />
	

	<div class="container d-grid gap-4">
		<div class="col-sm" id="promo">
			<img src="pic/performance.jpg" class="img-fluid" alt="mobile">
		</div>
		</div>
		
	<div class="container col-sm-12 col-md-10 col-lg-9 col-xl-9 mx-auto d-grid gap-4">
		
		<div class="row">
			<h1 class="h3 mb-2 py-2 text-center text-primary"><fmt:message key="main.motto"/></h1>
			<div class="row mb-3 display-4 justify-content-center mx-auto "><fmt:message key="main.tariffs"/></div>
			<c:forEach var="plan" items="${requestScope.all_plans}">
				<div class="col-sm-4 mb-3 mb-md-0">
					<div class="card text-center h-100">
						<div class="card-body d-flex flex-column">
							<div class="mb-4">
								<h3>${plan.name}</h3>
								<span class="display-4">${plan.regularPayment/100}</span> <br />
								<span class="display-6"><fmt:message key="plan.rub.month"/></span>
								<p class="lead text-center mb-4"><fmt:message key="plan.regular.payment"/>
</p>
							</div>

							<div class="mb-4">
								<p class="lead text-center mb-4">${plan.description}</p>
							</div>
							<div class="mt-auto">
								<a
									href="${pageContext.request.contextPath}/controller?command=show_full_plan&id=${plan.id}"
									class="btn btn-lg btn-outline-primary"><fmt:message key="main.details"/></a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row">
		<div class="row mb-2 display-4 justify-content-center mx-auto "><fmt:message key="main.news"/></div>
			<c:forEach var="article" items="${requestScope.news}" begin="0"
				end="2">
				<div class="col-sm-4 fw-light" >
					<span class="fs-5"><b>${article.title}</b></span>
					<br/><em> ${article.date}</em><br/>
					<p>
						${article.lead} <br /> <a
							href="${pageContext.request.contextPath}/controller?command=show_full_article&id=${article.id}"
							class="card-link"><fmt:message key="main.details"/></a>
					</p>
				</div>
			</c:forEach>
		</div>
		<form class=" d-grid gap-2 col-4" action="controller" method="GET">
			<input type="hidden" name="command" value="provide_news" /> <input
				type="hidden" name="path" value="all_news" /> <input type="submit"
				value="<fmt:message key="main.all.news"/>" class="btn btn-outline-primary " />
		</form>
	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>