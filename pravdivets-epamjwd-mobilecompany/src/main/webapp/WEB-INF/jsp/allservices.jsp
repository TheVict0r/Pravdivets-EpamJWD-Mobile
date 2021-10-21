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
<link rel="shortcut icon" href="pic/mobile.ico"/>
<title>mobile</title>
</head>

<body class="d-flex flex-column min-vh-100 bg-light text-dark">
	<jsp:include page="components/header.jsp" />

	<div class="col-sm" id="promo">
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>
		<div class="row display-4 justify-content-center py-4 mx-auto ">Услуги</div>
		<div class="tab-pane tab-content col-xs-12 col-sm-11 col-md-10 col-lg-9 col-xl-8 col-xxl-7 mx-auto">
						<div class="row py-4">
							<c:forEach var="service" items="${requestScope.all_services}">
								<div class="col-sm-4 mb-3 mb-md-4">
									<div class="card text-center h-100">
										<div class="card-body d-flex flex-column">
											<div class="mb-4">
												<h3>${service.name}</h3>
												<span class="display-4">${service.tarif/100}</span> <span
													class="display-6">руб.</span>
											</div>
											<div class="mt-auto">
												<a href="${pageContext.request.contextPath}/controller?command=show_full_service&id=${service.id}" class="btn btn-lg btn-outline-primary">Подробнее</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>