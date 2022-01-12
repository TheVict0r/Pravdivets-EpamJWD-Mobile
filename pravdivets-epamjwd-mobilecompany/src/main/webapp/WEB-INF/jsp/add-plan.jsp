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
	<div class="row justify-content-center display-4 mx-auto mb-1">
		<fmt:message key="add-plan.new-plan" />
	</div>

	<div
		class="row justify-content-center col col-lg-6 fw-normal text-center mx-auto text-danger fw-normal">
		<c:if test="${sessionScope.error eq 'plan_exists'}">

			<fmt:message key="add-plan.plan-exist" />
		</c:if>
		<c:remove var="error" />
	</div>

	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="post" action=controller?command=add_plan>
			<table>
				<tr>
					<td><label for="name" class="form-label"><fmt:message
								key="add-plan.name" />:</label></td>
					<td><input type="text" class="form-control" name="name"
						id="name" value="${sessionScope.name}" required> <c:remove
							var="name" /></td>
				</tr>
				<tr>
					<td><label for="description" class="form-label"><fmt:message
								key="add-plan.description" />:</label></td>
					<td><textarea class="form-control" name="description"
							id="description" rows="2" cols="40" required>
						${sessionScope.description} 
						</textarea> <c:remove var="description" /></td>
				</tr>
				<tr>
					<td><label for="regular_payment" class="form-label"><fmt:message
								key="add-plan.regular-payment" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="regular_payment" id="regular_payment"
						value="${sessionScope.regular_payment}" required> <c:remove
							var="regular_payment" /></td>
				</tr>
				<tr>
					<td><label for="upfront_payment" class="form-label"><fmt:message
								key="add-plan.upfront-payment" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="upfront_payment" id="upfront_payment"
						value="${sessionScope.upfront_payment}" required> <c:remove
							var="upfront_payment" /></td>
				</tr>
				<tr>
					<td><label for="within_network" class="form-label"><fmt:message
								key="add-plan.within-network" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="within_network" id="within_network"
						value="${sessionScope.within_network}" required> <c:remove
							var="within_network" /></td>
				</tr>
				<tr>
					<td><label for="other_networks" class="form-label"><fmt:message
								key="add-plan.other-networks" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="other_networks" id="other_networks"
						value="${sessionScope.other_networks}" required> <c:remove
							var="other_networks" /></td>
				</tr>
				<tr>
					<td><label for="abroad" class="form-label"><fmt:message
								key="add-plan.abroad" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="abroad" id="abroad" value="${sessionScope.abroad}" required>
						<c:remove var="abroad" /></td>
				</tr>
				<tr>
					<td><label for="videocall" class="form-label"><fmt:message
								key="add-plan.videocall" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="videocall" id="videocall" value="${sessionScope.videocall}"
						required> <c:remove var="videocall" /></td>
				</tr>
				<tr>
					<td><label for="sms" class="form-label"><fmt:message
								key="add-plan.sms" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="sms" id="sms" value="${sessionScope.sms}" required>
						<c:remove var="sms" /></td>
				</tr>
				<tr>
					<td><label for="mms" class="form-label"><fmt:message
								key="add-plan.mms" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="mms" id="mms" value="${sessionScope.mms}" required>
						<c:remove var="mms" /></td>
				</tr>
				<tr>
					<td><label for="internet" class="form-label"><fmt:message
								key="add-plan.internet" />:</label></td>
					<td><input type="number" min="0" class="form-control"
						name="internet" id="internet" value="${sessionScope.internet}"
						required> <c:remove var="internet" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-outline-dark"
						value="OK"></td>
				</tr>
			</table>

		</form>
	</div>
	<div class="d-grid col-3 py-2 mx-auto ">
		<a class="btn btn-outline-dark"
			href="${pageContext.request.contextPath}/controller?command=go_to_plan_operations_page"><fmt:message
				key="add-subscriber.back" /></a>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>