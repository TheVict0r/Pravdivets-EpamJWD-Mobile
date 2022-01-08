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
<body class="d-flex flex-column min-vh-100 bg-light">

	<jsp:include page="components/header.jsp" />

	<div class="container col-sm-12 col-md-10 col-lg-9 col-xl-9  fs-5 fw-light flex-grow-1">

		<div class="display-3 text-center  py-5 flex-grow-1">
			<fmt:message key="admin.title" />
		</div>


			<div class="d-grid gap-4  col-6 py-4 mx-auto">
			  <a class="btn btn-outline-dark fs-5"
				href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_operations_page">
				<fmt:message key="admin.subscriber-operations" />
			</a>
			  <a class="btn btn-outline-dark fs-5"
				href="${pageContext.request.contextPath}/controller?command=go_to_consultant_operations_page">
				<fmt:message key="admin.consultant-operations" />
			</a>
			<a class="btn btn-outline-dark fs-5"
				href="${pageContext.request.contextPath}/controller?command=go_to_plan_operations_page">
				<fmt:message key="admin.plan-operations" />
			</a>
			<a class="btn btn-outline-dark fs-5"
				href="${pageContext.request.contextPath}/controller?command=go_to_service_operations_page">
				<fmt:message key="admin.service-operations" />
			</a>
			<a class="btn btn-outline-dark fs-5"
				href="${pageContext.request.contextPath}/controller?command=go_to_article_operations_page">
				<fmt:message key="admin.article-operations" />
			</a>
	</div>
	</div>

	<jsp:include page="components/footer.jsp" />
</body>
</html>