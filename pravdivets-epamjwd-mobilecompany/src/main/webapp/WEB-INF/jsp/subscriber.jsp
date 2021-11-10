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
		class="container col-sm-12 col-md-10 col-lg-9 col-xl-7 fs-6 fw-light flex-grow-1">
		<table class="table ">
			<tr>
				<c:choose>
					<c:when test="${sessionScope.activate_edit eq 'phone'}">
						<td>ЧЕНЬДЖ ПХОНЕ!!!</td>
						<td><a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=change_phone"><fmt:message
									key="subscriber.edit" /></a></td>
					</c:when>
					<c:otherwise>
						<td><fmt:message key="subscriber.phone" /></td>
						<td>${sessionScope.phone_format}</td>
						<td><a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=prepare_change_phone"><fmt:message
									key="subscriber.edit" /></a></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<c:choose>
				<c:when test="${sessionScope.activate_edit eq 'personal_data'}">
					<tr>
						<td><label for="subscriber_user_last_name" class="form-label"><fmt:message
									key="add-subscriber.last-name" />:</label></td>
						<td><input type="text" class="form-control"
							name="subscriber_user_last_name" id="subscriber_user_last_name"
							pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$"
							value="${sessionScope.subscriber_user.lastName}" required>
							</td>
					</tr>
					<tr>
						<td><label for="subscriber_user_first_name"
							class="form-label"><fmt:message
									key="add-subscriber.first-name" />:</label></td>
						<td><input type="text" class="form-control"
							name="subscriber_user_first_name" id="subscriber_user_first_name"
							pattern="^[A-ZА-ЯЁ][a-zа-яё\-]+$"
							value="${sessionScope.subscriber_user.firstName}" required>
							</td>
					</tr>
					<tr>
						<td><label for="subscriber_user_middle_name"
							class="form-label"><fmt:message
									key="add-subscriber.middle-name" />:</label></td>
						<td><input type="text" class="form-control"
							name="subscriber_user_middle_name"
							id="subscriber_user_middle_name" pattern="^[A-ZА-ЯЁ][a-zа-яё]+$"
							value="${sessionScope.subscriber_user.middleName}"></td>
					</tr>
					<tr>
						<td><label for="passport" class="form-label"><fmt:message
									key="add-subscriber.passport" />:</label></td>
						<td><input type="text" class="form-control" name="passport"
							id="email" value="${sessionScope.subscriber_user.passport}" required>
							</td>
					</tr>
					<tr>
						<td><label for="email" class="form-label">e-mail:</label></td>
						<td><input type="email" class="form-control" name="email"
							id="email" value="${sessionScope.subscriber_user.email}" required>
							</td>
					</tr>
					<tr>
					<td></td>
					<td><a class="login btn btn-outline-dark btn-sm"
						href="${pageContext.request.contextPath}/controller?command=cancel_edit_subscriber_data"><fmt:message
								key="subscriber.cancel" /></a></td>
					<td><a class="login btn btn-primary btn-sm"
						href="${pageContext.request.contextPath}/controller?command=edit_personal_data"><fmt:message
								key="subscriber.edit" /></a></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td><fmt:message key="subscriber.name" /></td>
						<td>${sessionScope.subscriber_user.firstName}
							${sessionScope.subscriber_user.middleName}
							${sessionScope.subscriber_user.lastName}</td>
					</tr>
					<tr>
						<td><fmt:message key="subscriber.passport" /></td>
						<td>${sessionScope.subscriber_user.passport}</td>
					</tr>
					<tr>
						<td>e-mail:</td>
						<td>${sessionScope.subscriber_user.email}</td>
						<td><a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=prepare_edit_personal_data"><fmt:message
									key="subscriber.edit-personal-data" /></a></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<c:choose>
					<c:when test="${sessionScope.activate_edit eq 'plan'}">
						<td>ЕСТЬ ЛИ У ВАС ПЛАН, МИСТЕР ФИКС?!!!</td>
						<td><a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=change_plan"><fmt:message
									key="subscriber.edit" /></a></td>
					</c:when>
					<c:otherwise>
						<td><fmt:message key="subscriber.plan" /></td>
						<td>${sessionScope.plan.name}</td>
						<td><a class="login btn btn-outline-primary btn-sm"
							href="${pageContext.request.contextPath}/controller?command=prepare_change_plan"><fmt:message
									key="subscriber.edit" /></a></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td><fmt:message key="subscriber.contract-date" /></td>
				<td>${sessionScope.subscriber.contractDate}</td>
				<td></td>
			</tr>
			<tr>
				<td><fmt:message key="subscriber.account-number" /></td>
				<td>${sessionScope.subscriber.id}</td>
				<td></td>
			</tr>
			<tr>
				<td><fmt:message key="subscriber.account-amount" /></td>
				<td>${sessionScope.subscriber.account/100}руб.</td>
				<td></td>
			</tr>
			<tr>
				<td><fmt:message key="subscriber.status" /></td>
				<td>${sessionScope.subscriber.status.statusName}</td>
				<td></td>
			</tr>
			<tr>
				<td><fmt:message key="subscriber.status-date" /></td>
				<td>${sessionScope.subscriber.statusDate}</td>
				<td></td>
			</tr>
		</table>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>