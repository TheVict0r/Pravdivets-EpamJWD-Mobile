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
	<div class="row align-content-center mx-auto flex-grow-1">
		<span class="fs-4 fw-light text-center">Пожалуйста, выберите
			тот номер телефона, <br />по которому Вы желаете получить информацию:
		</span>
	</div>
	<div class="fs-4 fw-bold text-center text-primary flex-grow-1">
		<c:forEach var="subscriber" items="${requestScope.subscriber_list}">
			<c:set value="${subscriber.phone}" var="phone" />
			<a
				href="${pageContext.request.contextPath}/controller?command=show_subscriber_by_id&id=${subscriber.id}">
				${subscriber.phone} </a>
			<br />
		</c:forEach>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>