<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<fmt:setLocale
	value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!-- bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<header id="header">
	<nav
		class="navbar sticky-top navbar-light navbar-expand-lg navigation-clean-button">
		<div class="container">
			<a class="navbar-brand text-primary fs-1 fw-bold me-2"
				href="controller?command=go_to_main_page">mobile</a>
			<button data-bs-toggle="collapse" data-bs-target="#navcol-1"
				class="navbar-toggler">
				<span class="visually-hidden">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/controller?command=show_all_plans"
						style="font-size: 15px;"><fmt:message key="header.tariffs" /></a></li>
					<li class="nav-link text-primary fw-bold">|</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/controller?command=go_to_calculator_page"
						style="font-size: 15px;"><fmt:message
								key="header.tariff-calculator" /></a></li>
					<li class="nav-link text-primary fw-bold">|</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/controller?command=show_all_services"
						style="font-size: 15px;"><fmt:message key="header.services" /></a></li>
					<li class="nav-link text-primary fw-bold">|</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/controller?command=show_all_news"
						style="font-size: 15px;"><fmt:message key="header.news" /></a></li>
				</ul>
				
				
				<div class="navbar actions text-end">
					<ul class="navbar-nav me-auto">
						<c:choose>
							<c:when test="${sessionScope.session_locale == 'ru' || sessionScope.session_locale == null}">
								<li class="nav-item "><a
									class="nav-link fw-bold text-dark text-decoration-underline"
									href="${pageContext.request.contextPath}/controller?command=switch_locale&session_locale=ru">RU</a>
								</li>
								<li class="nav-link text-primary fw-bold">|</li>
								<li class="nav-item "><a class="nav-link me-md-3"
									href="${pageContext.request.contextPath}/controller?command=switch_locale&session_locale=en">EN</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item "><a class="nav-link"
									href="${pageContext.request.contextPath}/controller?command=switch_locale&session_locale=ru">RU</a>
								</li>
								<li class="nav-link text-primary fw-bold">|</li>
								<li class="nav-item "><a
									class="nav-link fw-bold text-dark text-decoration-underline me-md-3"
									href="${pageContext.request.contextPath}/controller?command=switch_locale&session_locale=en">EN</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
					</div>
					
					<div class="navbar actions text-end">
					<c:choose>
						<c:when test="${sessionScope.last_name == null}">
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<a
									class="btn btn-outline-primary me-md-1" type="button"
									href="${pageContext.request.contextPath}/controller?command=go_to_login_page"><fmt:message
											key="header.sign-in" /></a>
								<a 
									class="btn btn-primary" type="button" 
									href="${pageContext.request.contextPath}/controller?command=go_to_signup_page"><fmt:message key="header.sign-up" /></a>
							</div>
						</c:when>
						<c:otherwise>
							<ul class="navbar-nav me-auto">
								<li class="nav-item fw-bold"><a class="nav-link text-dark me-md-3"
									href="${pageContext.request.contextPath}/controller?command=go_to_profile_page">${sessionScope.first_name}
										${sessionScope.last_name}</a></li>
								<li class="nav-item"><a class="btn btn-primary me-2"
									href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message
											key="header.sign-out" /></a></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</nav>
</header>
