<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	<div class="col-sm" id="promo">
		<img src="pic/article.jpg" class="img-fluid" alt="mobile">
	</div>

	<div class="container p-0">

		<h1 class="h3 mb-3 text-center">Plans &amp; Pricing</h1>

		<div class="row">
			<div class="col-md-12 col-xl-10 mx-auto">
				<h1 class="text-center">We have a plan for everyone</h1>
				<p class="lead text-center mb-4">Whether you're a business or an individual, 14-day trial no credit card required.</p>

				<div class="tab-content">
					<div class="tab-pane fade show active" id="monthly">
						<div class="row py-5">


							<c:forEach var="plan" items="${requestScope.all_plans}">

								<div class="col-sm-4 mb-3 mb-md-4">
									<div class="card text-center h-100 ">
										<div class="card-body d-flex flex-column">
											<div class="mb-4">
												<h3>${plan.name}</h3>
												<span class="display-4">${plan.regularPayment/100}</span> </br> <span
													class="display-6">руб/мес</span>
												<p class="lead text-center mb-4">абонентская плата</p>
											</div>
											
											<div class="mb-4">
											<p class="lead text-center mb-4">${plan.description}</p>
											</div>
											
											<h6>Звонки:</h6>
											<ul class="list-unstyled">
												<li class="mb-2"> <b> ${plan.priceWithinNetwork/100} руб. </b> - внутри сети</li>
												<li class="mb-2"> <b> ${plan.priceOtherNetworks/100} руб. </b> - в другие сети</li>
												<li class="mb-2"> <b> ${plan.priceAbroad/100} руб. </b> - международные</li>
												<li class="mb-2"> <b> ${plan.priceVideocall/100} руб. </b> - видеозвонки</li>
												<li class="mb-2"> <b> ${plan.priceSMS/100} руб. </b> - за 1 SMS</li>
												<li class="mb-2"> <b> ${plan.priceMMS/100} руб. </b> - за 1 MMS</li>
											</ul>
											<div class="mt-auto">
												<a href="${pageContext.request.contextPath}/controller?command=full_plan&id=${plan.id}" class="btn btn-lg btn-outline-primary">Подробнее</a>
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


	</div>
	<br />
	<jsp:include page="components/footer.jsp" />
</body>
</html>