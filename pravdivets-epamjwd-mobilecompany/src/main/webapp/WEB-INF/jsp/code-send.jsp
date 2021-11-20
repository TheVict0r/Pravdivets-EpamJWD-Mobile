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
	<div class="row justify-content-center display-4 py-5 mx-auto ">
		<fmt:message key="code-request.title" />
	</div>
		<div class="row justify-content-center mx-auto fw-light flex-grow-1">
				<div
					class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto fs-5">
					<fmt:message key="code-request.lead" />
				</div>
				<div class="justify-content-center text-center mx-auto">
					<a class="btn btn-outline-dark" 
					href="${pageContext.request.contextPath}/controller?command=code_send">
						<fmt:message key = "code-request.get-code"/></a>
				</div>
		</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>