<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale
	value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="${sessionScope.session_locale}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>

<body class="d-flex flex-column min-vh-100 bg-light">
	<jsp:include page="components/header.jsp" />

	<div class="col-sm" id="promo">
		<img src="pic/news.jpg" class="img-fluid" alt="mobile">
	</div>

	<div class="row display-4 justify-content-center py-4 mx-auto ">
		<fmt:message key="allnews.news" />
	</div>
	<div
		class="tab-pane tab-content col-xs-11 col-sm-10 col-md-9 col-lg-8 col-xl-7 col-xxl-6 mx-auto">

		<c:forEach var="article" items="${sessionScope.news}">
			<h5>${article.title}</h5>
			<em> ${article.date}</em>
			<br />
			<p>
				${article.lead} <br /> <a
					href="${pageContext.request.contextPath}/controller?command=show_full_article&id=${article.id}"
					class="card-link"><fmt:message key="allnews.detais" /></a>
			</p>

			<br />
		</c:forEach>

		<div class="justify-content-center">

			<table>
				<tr>
					<td><c:choose>
							<c:when test="${sessionScope.no_next_news eq 'true' }">
								<div class="d-grid mb-3 mx-auto me-md-5">
									<button type="button" class="btn btn-outline-secondary"
										disabled>
										<fmt:message key="allnews.next" />
									</button>
								</div>

							</c:when>
							<c:otherwise>
								<div class="d-grid  mb-3 mx-auto me-md-5">
									<a class="btn btn-outline-primary"
										href="${pageContext.request.contextPath}/controller?command=show_next_news"><fmt:message
											key="allnews.next" /></a>
								</div>
							</c:otherwise>
						</c:choose></td>
					<td class="d-grid  mb-3 mx-auto me-md-5"></td>
					<td><c:choose>
							<c:when test="${sessionScope.no_previous_news eq 'true' }">
								<div class="d-grid mb-3 mx-auto me-md-5">
									<button type="button" class="btn btn-outline-secondary"
										disabled>
										<fmt:message key="allnews.previous" />
									</button>
								</div>
							</c:when>
							<c:otherwise>
								<div class="d-grid mb-3 mx-auto me-md-5">
									<a class="btn btn-outline-primary"
										href="${pageContext.request.contextPath}/controller?command=show_previous_news"><fmt:message
											key="allnews.previous" /></a>
								</div>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>
		</div>

	</div>

	<jsp:include page="components/footer.jsp" />
</body>
</html>