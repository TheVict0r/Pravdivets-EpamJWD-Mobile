<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<header id="header">
	<nav
		class="navbar navbar-light navbar-expand-md navigation-clean-button">
		<div class="container">
			<a class="navbar-brand"
				href="controller?command=provide_news&path=main"><strong>mobile</strong></a>
			<button data-bs-toggle="collapse" data-bs-target="#navcol-1"
				class="navbar-toggler">
				<span class="visually-hidden">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/controller?command=provide_news&path=all_news"
						style="font-size: 15px;">новости</a></li>
					<li class="nav-item"><a class="nav-link" href="#"
						style="font-size: 15px;">услуги</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/controller?command=provide_all_plans&path=all_plans"
						style="font-size: 15px;">тарифы</a></li>

					<li class="nav-item"><a class="nav-link" href="#"
						style="font-size: 15px;">калькулятор тарифов</a></li>
				</ul>
				<div class="text-end">
					<span class="navbar actions"> <a
						class="login btn btn-outline-primary me-2"
						href="${pageContext.request.contextPath}/controller?command=login&path=login">Вход</a>
						<a class="btn btn-primary" role="button" href="#">Регистрация</a></span>
				</div>
			</div>
		</div>
	</nav>
</header>