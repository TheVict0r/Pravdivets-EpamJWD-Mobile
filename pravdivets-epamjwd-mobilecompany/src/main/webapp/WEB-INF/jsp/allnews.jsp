<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="${sessionScope.session_locale}">
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

			<div class="row display-4 justify-content-center py-4 mx-auto ">Новости</div>
			<div class="tab-pane tab-content col-xs-11 col-sm-10 col-md-9 col-lg-8 col-xl-7 col-xxl-6 mx-auto">
	
		<c:forEach var="article" items="${requestScope.news}">
				<h5>${article.title}</h5>
				<em> ${article.date}</em> <br />
				<p>
					${article.lead} <br /> <a
						href="${pageContext.request.contextPath}/controller?command=show_full_article&id=${article.id}"
						class="card-link">Подробнее</a>
				</p>
			
			<br />
		</c:forEach>

	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>