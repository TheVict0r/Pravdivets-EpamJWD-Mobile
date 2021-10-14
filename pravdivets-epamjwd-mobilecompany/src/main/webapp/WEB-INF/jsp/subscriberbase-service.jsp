<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}" />
<fmt:setBundle basename="language" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

	<jsp:include page="components/header.jsp"/>

	<div class="container col-sm-12 col-md-10 col-lg-9 col-xl-9 fs-5 fw-light flex-grow-1">
		<h3 class="mt-4 mb-3"><fmt:message key="consultant.show.subscriber"/></h3>
			<div class="col-md-12 mb-3">
				<c:if test="${requestScope.error eq 'error_null_subscriber'}">
					<span class="fs-4 text-danger">
						<fmt:message key="consultant.null.subscriber"/>
						<c:remove var="error" />
					</span>
				</c:if>
			</div>
		
		<form class="row mb-4" method="POST" action="controller?command=show_subscriber_by_phone">
			<label for="phoneNumber" class="form-label"><fmt:message key="consultant.by.phone"/> <span class="fs-6 fst-italic">(<fmt:message key="consultant.nine.digits"/> <b>25xxxxxxx, 29xxxxxxx, 33xxxxxxx, 44xxxxxxx</b>)</span></label>
			<div class="col-md-3">
				<input type="text" class="form-control" id="phoneNumber" name="phone_number" value="${requestScope.phone_number}" placeholder="291234567" pattern="^(25|29|33|44)[0-9]{7}$" required>
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>

		<form class="row mb-5" method="POST" action="controller?command=show_subscriber_list_by_full_name">
			<label for="lastName" class="form-label"><fmt:message key="consultant.by.name"/></label>
			<div class="col-md-3">
				<input type="text" class="form-control" id="lastName" name="last_name" value="${requestScope.last_name}"
					placeholder="<fmt:message key="consultant.last.name"/>"  pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="firstName" name="first_name" value="${requestScope.first_name}"
					placeholder="<fmt:message key="consultant.first.name"/>" pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$" required>
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" id="middleName" name="middle_name" value="${requestScope.middle_name}"
					placeholder="<fmt:message key="consultant.middle.name"/>" pattern="^[A-ZА-ЯЁ][a-zа-яё]+$">
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-outline-dark" value="OK">
			</div>
		</form>
				<h3 class="mt-4"><fmt:message key="consultant.add.subscriber"/></h3>
		
		
	</div>




	<jsp:include page="components/footer.jsp" />
</body>
</html>