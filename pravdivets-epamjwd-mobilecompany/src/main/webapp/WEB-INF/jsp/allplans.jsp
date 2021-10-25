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
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>

<body class="d-flex flex-column min-vh-100 bg-light text-dark">
	<jsp:include page="components/header.jsp" />

	<div class="col-sm" id="promo">
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>

	<div class="container p-0">

		<h1 class="h3 py-4 mb-2 text-center"><fmt:message key="allplans.title"/></h1>

		<div class="row">
			<div class="col-md-12 col-xl-10 mx-auto">
				<h1 class="text-center "><fmt:message key="allplans.motto"/></h1>
					<div class="tab-content tab-pane fade show active" id="monthly">
						<div class="row py-5">
							<c:forEach var="plan" items="${requestScope.all_plans}">
								<div class="col-sm-4 mb-3 mb-md-4">
									<div class="card text-center h-100 ">
										<div class="card-body d-flex flex-column">
											<div class="mb-4">
												<h3>${plan.name}</h3>
												<span class="display-4">${plan.regularPayment/100}</span> </br> <span
													class="display-6"><fmt:message key="plan.rub-month"/></span>
												<p class="lead text-center mb-4"><fmt:message key="plan.regular-payment"/></p>
											</div>
											
											<div class="mb-4">
											<p class="lead text-center mb-4">${plan.description}</p>
											</div>
											<div class="mt-auto">
												<a href="${pageContext.request.contextPath}/controller?command=show_full_plan&id=${plan.id}" class="btn btn-lg btn-outline-primary"><fmt:message key="allplans.detais"/></a>
											</div>
										</div>
									</div>
								</div>

							</c:forEach>

						</div>
					</div>

				</div>
			</div>
		</div>



	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>