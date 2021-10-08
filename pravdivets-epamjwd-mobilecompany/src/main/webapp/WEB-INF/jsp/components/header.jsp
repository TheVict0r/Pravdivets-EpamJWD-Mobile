<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<header id="header">

	<nav
		class="navbar navbar-light navbar-expand-lg navigation-clean-button">
		<div class="container">
			<a class="navbar-brand text-primary fs-1 fw-bold"
				href="controller?command=go_to_main_page">mobile</a>
			<button data-bs-toggle="collapse" data-bs-target="#navcol-1"
				class="navbar-toggler">
				<span class="visually-hidden">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_all_news"
						style="font-size: 15px;">новости</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_all_plans"
						style="font-size: 15px;">тарифы</a></li>
					<li class="nav-item"><a class="nav-link" href="#"
						style="font-size: 15px;">калькулятор тарифов</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_all_services"
						style="font-size: 15px;">услуги</a></li>
				</ul>
				<div class="navbar actions text-end">
				<c:choose>
					<c:when test="${sessionScope.last_name == null}">
						<a class="login btn btn-outline-primary btn-sm me-2" href="${pageContext.request.contextPath}/controller?command=go_to_login_page">Вход</a>
						<a class="btn btn-primary btn-sm " role="button" href="#">Регистрация</a>
					</c:when>
					
					<c:when test="${sessionScope.last_name != null}">
					<span class="fw-bold text-secondary">
						${sessionScope.first_name}
						${sessionScope.last_name} 
					</span>
					<span>
						<a class="btn btn-primary btn-sm me-2" href="${pageContext.request.contextPath}/controller?command=logout">Выйти</a>
					</span>
					</c:when>
				</c:choose>
				</div>
			</div>
		</div>
	</nav>
</header>