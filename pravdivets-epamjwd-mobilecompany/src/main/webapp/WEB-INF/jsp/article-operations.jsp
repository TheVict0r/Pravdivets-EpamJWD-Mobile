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
	<div
		class="container col-sm-12 col-md-10 col-lg-8 col-xl-8 fw-light flex-grow-1">

		<div class="display-5 text-start py-4 flex-grow-1">
			<fmt:message key="article-operations.title" />
		</div>
		<div>
			<h4 class="mb-3">
				<fmt:message key="article-operations.find" />
			</h4>
		</div>
		<div class="col-md-12">
			<c:if test="${sessionScope.error eq 'no_article'}">
				<span class="fw-normal fs-6 text-danger"> <fmt:message
						key="article-operations.no-article" />
				</span>
			</c:if>
			<c:remove var="error" />
		</div>
		<form class="row mb-3" method="POST"
			action="controller?command=find_article_by_id_admin">
			<label for="article_id" class="form-label"><fmt:message
					key="article-operations.find-by-id" /></label>
			<div class="col-md-3">
				<input type="number" class="form-control" name="article_id" id="article_id"
					placeholder="12" value="${sessionScope.article_id}"
					required>
				<c:remove var="article_id" />
			</div>
			<div class="col-md-2">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>
		<form class="row mb-4" method="POST"
			action="controller?command=find_article_by_title">
			<label for="passport" class="form-label"><fmt:message
					key="article-operations.find-by-title" /></label>
			<div class="col-md-3">
				<input type="text" class="form-control" name="title"
					id="title"  value="${sessionScope.title}" required>
					<c:remove var="title"/>
			</div>
			<div class="col-md-2">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>
		<div>
			<h4 class="py-2">
				<fmt:message key="article-operations.add" />
			</h4>
		</div>
		<div class="d-grid col-1 py-1 mb-4 mx-start ">
			<a class="btn btn-outline-dark"
				href="${pageContext.request.contextPath}/controller?command=go_to_add_article_page">
				<fmt:message key="admin.add" />
			</a>
		</div>
		<div class="d-grid col-5 py-5 mx-start ">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
					key="admin.to-admin" /></a>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>