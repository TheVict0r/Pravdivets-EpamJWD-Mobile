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
	<div class="container text-center mx-auto flex-grow-1">
		<span class="display-5 fw-light"><fmt:message
				key="subscriber.title" /></span>
	</div>
	<div
		class="container col-sm-12 col-md-12 col-lg-8 col-xl-7 py-3 fs-6 fw-light flex-grow-1">
		<table class="table">
			<c:choose>
				<c:when test="${sessionScope.activate_edit eq 'phone'}">
					<tr class="fw-normal table-secondary ">
						<td><fmt:message key="subscriber.phone-current" /></td>
						<td>${sessionScope.phone_format}</td>
						<td></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td><fmt:message key="subscriber.phone-new" /></td>
						<td>${sessionScope.new_phone_format}</td>
						<td><a class="btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=change_phone_preparation"><fmt:message
									key="subscriber.reload" /></a></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td></td>
						<td><a class="btn btn-secondary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=cancel_edit_subscriber_data"><fmt:message
									key="subscriber.cancel" /></a></td>
						<td><a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=change_phone"><fmt:message
									key="subscriber.edit" /></a></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td><fmt:message key="subscriber.phone" /></td>
						<td>${sessionScope.phone_format}</td>
				<td><c:if test="${sessionScope.role != 'SUBSCRIBER'}">		
						<a class="btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=change_phone_preparation"><fmt:message
									key="subscriber.edit" /></a>
					</c:if></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<form method="post" action=controller?command=edit_personal_data>
				<div
					class="row justify-content-center col col-lg-8 fw-normal text-center mx-auto text-danger fw-normal">
					<p>
						<c:choose>
							<c:when test="${sessionScope.error == 'booked_passport'}">
								<fmt:message key="subscriber.booked-passport" />
							</c:when>
							<c:when test="${sessionScope.error == 'booked_email'}">
								<fmt:message key="subscriber.booked-email" />
							</c:when>
						</c:choose>
						<c:remove var="error" />
					</p>
				</div>
			<c:choose>
				<c:when test="${sessionScope.activate_edit eq 'personal_data'}">
					<tr class="fw-normal table-secondary">
						<td><label for="subscriber_last_name" class="form-label"><fmt:message
									key="add-subscriber.last-name" />:</label></td>
						<td><input type="text" class="form-control"
							name="subscriber_last_name" id="subscriber_last_name"
							pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
							value="${sessionScope.subscriber_user.lastName}" required>
						</td>
						<td></td>
					
					<tr class="fw-normal table-secondary">
						<td><label for="subscriber_first_name"
							class="form-label"><fmt:message
									key="add-subscriber.first-name" />:</label></td>
						<td><input type="text" class="form-control"
							name="subscriber_first_name" id="subscriber_first_name"
							pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
							value="${sessionScope.subscriber_user.firstName}" required>
						</td>
						<td></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td><label for="subscriber_middle_name"
							class="form-label"><fmt:message
									key="add-subscriber.middle-name" />:</label></td>
						<td><input type="text" class="form-control"
							name="subscriber_middle_name"
							id="subscriber_middle_name" 
							pattern="^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$"
							value="${sessionScope.subscriber_user.middleName}"></td>
						<td></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td><label for="passport" class="form-label"><fmt:message
									key="add-subscriber.passport" />:</label></td>
						<td><input type="text" class="form-control" name="passport"
							id="email" value="${sessionScope.subscriber_user.passport}"
							required></td>
						<td></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td><label for="email" class="form-label">e-mail:</label></td>
						<td><input type="email" class="form-control" name="email"
							id="email" value="${sessionScope.subscriber_user.email}" required>
						</td>
						<td></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td></td>
						<td><a class="btn btn-secondary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=cancel_edit_subscriber_data"><fmt:message
									key="subscriber.cancel" /></a></td>
						<td>
						<input type="submit" class="btn btn-primary btn-sm"
							value="<fmt:message key="subscriber.edit"/>">
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td><fmt:message key="subscriber.name" /></td>
						<td>${sessionScope.subscriber_user.firstName}
							${sessionScope.subscriber_user.middleName}
							${sessionScope.subscriber_user.lastName}</td>
							<td></td>
					</tr>
					<tr>
						<td><fmt:message key="subscriber.passport" /></td>
						<td>${sessionScope.subscriber_user.passport}</td>
						<td></td>
					</tr>
					<tr>
						<td>e-mail:</td>
						<td>${sessionScope.subscriber_user.email}</td>
						<td><c:if test="${sessionScope.role != 'SUBSCRIBER'}">	
						<a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=edit_personal_data_preparation"><fmt:message
									key="subscriber.edit-personal-data" /></a>
						</c:if></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</form >
			<form method="post" action=controller?command=change_plan>
			<c:choose>
				<c:when test="${sessionScope.activate_edit eq 'plan'}">
					<tr class="fw-normal table-secondary">
						<td><fmt:message key="subscriber.plan-current" /></td>
						<td>${sessionScope.plan.name}</td>
						<td></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td><fmt:message key="subscriber.plan-new" /></td>
						<td><select class="form-select form-select-sm" id="plan"
							name="plan_id">
								<c:forEach var="plan" items="${sessionScope.all_plans}">
									<option value="${plan.id}"> ${plan.name} </option>
								</c:forEach>
						</select></td>
						<td><fmt:message key="subscriber.plan-select" /></td>
					</tr>
					<tr class="fw-normal table-secondary">
						<td></td>
						<td><a class="btn btn-secondary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=cancel_edit_subscriber_data"><fmt:message
									key="subscriber.cancel" /></a></td>
						<td><input type="submit" class="btn btn-primary btn-sm"
							value="<fmt:message key="subscriber.edit"/>"></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td><fmt:message key="subscriber.plan" /></td>
						<td>${sessionScope.plan.name}</td>
						<td><a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=change_plan_preparation"><fmt:message
									key="subscriber.edit" /></a></td>
					</tr>
				</c:otherwise>
			</c:choose>
			</form>
			<tr>
			<td><fmt:message key="subscriber.account-number"/></td>
			<td>${sessionScope.subscriber.id}</td>
			<td></td>
			</tr>
			<tr>
				<td><fmt:message key="subscriber.account-amount" /></td>
				<td>${sessionScope.subscriber.account/100} <fmt:message key="subscriber.rub" /></td>
				<td><a class="btn btn-outline-primary btn-sm"
		href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_bills_page">
		<fmt:message key="subscriber.bills" /></a></td>
			</tr>
	
				<form method="post" action=controller?command=change_status>
				<c:choose>
					<c:when test="${sessionScope.activate_edit eq 'status'}">
					<tr class="fw-normal table-secondary">
					<td><fmt:message key="subscriber.current-status" /></td>
					<td>${sessionScope.subscriber.status.statusName}</td>
					<td></td>
				</tr>
					<tr class="fw-normal table-secondary">
				<td><fmt:message key="subscriber.new-status"/></td>
						<td><select class="form-select form-select-sm" id="new_status" name="new_status">
									<option value="0"> <fmt:message key="subscriber.status-active"/> </option>
									<option value="1"> <fmt:message key="subscriber.status-semi-blocked"/> </option>
									<option value="2"> <fmt:message key="subscriber.status-blocked"/> </option>
									<option value="3"> <fmt:message key="subscriber.status-deactivated"/> </option>
									<option value="4"> <fmt:message key="subscriber.status-paused"/> </option>
						</select></td>
					<td></td>
				</tr>
					<tr class="fw-normal table-secondary">
						<td></td>
						<td><a class="btn btn-secondary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=cancel_edit_subscriber_data"><fmt:message
									key="subscriber.cancel" /></a></td>
						<td><input type="submit" class="btn btn-primary btn-sm"
							value="<fmt:message key="subscriber.edit"/>"></td>
					</tr>
					</c:when>
					<c:otherwise>
				<tr>
					<td><fmt:message key="subscriber.status" /></td>
					<td>${sessionScope.subscriber.status.statusName}</td>
					<td><c:if test="${sessionScope.role != 'SUBSCRIBER'}">
						<a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=change_status_preparation"><fmt:message
									key="subscriber.edit" /></a>
					</c:if></td>
				</tr>
					</c:otherwise>
				</c:choose>
				</form>
			<tr>
				<td><fmt:message key="subscriber.status-date"/></td>
				<td>${sessionScope.subscriber.statusDate}</td>
				<td></td>
			</tr>
			<tr>
			<td><fmt:message key="subscriber.services"/></td>
			<td><a class="btn btn-outline-dark btn-sm"
		href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_services_page">
		<fmt:message key="subscriber.services-page"/></a></td>
		<td></td>
			</tr>
		</table>
		<c:if test="${sessionScope.role != 'SUBSCRIBER'}">
		<a class="btn btn-primary btn-sm"
		href="${pageContext.request.contextPath}/controller?command=go_to_subscriber_operations_page">
		<fmt:message key="subscriber.operations-page" /></a>
		</c:if>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>