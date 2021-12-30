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
	<div class="row justify-content-center display-4 mx-auto py-1 mb-1">
		<fmt:message key="add-article.new-article" />
	</div>
	<div class="row justify-content-center mx-auto fw-light flex-grow-1">
		<form method="post" action=controller?command=add_article>
			<table>
				<tr>
				<tr>
					<td><label for="title" class="form-label"><fmt:message
								key="add-article.title" />:</label></td>
				</tr>
				<tr>
				<td><input type="text" class="form-control" name="title"
					id="title" value="${sessionScope.title}" required> <c:remove
						var="title" /></td>
				</tr>
				<tr>
					<td><label for="lead" class="form-label"><fmt:message
								key="add-article.lead" />:</label></td>
				</tr>
				<tr>
					<td><textarea class="form-control" name="lead" id="lead"
							rows="2" cols="80" required>
						${sessionScope.lead} 
						</textarea> <c:remove var="lead" /></td>
				</tr>
				<tr>
					<td><label for="text" class="form-label"><fmt:message
								key="add-article.text" />:</label></td>
				</tr>
				<tr>
					<td><textarea class="form-control" name="text" id="text"
							rows="4" cols="80" required>
						${sessionScope.text} 
						</textarea> <c:remove var="text" /></td>
				</tr>
				<tr>
					<td><input type="submit" class="btn btn-outline-dark"
						value="OK"></td>
				</tr>
			</table>

		</form>
	</div>

	<div class="d-grid col-3 py-5 mx-auto ">
		<a class="btn btn-outline-dark"
			href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
				key="add-subscriber.back" /></a>
	</div>

	<jsp:include page="components/footer.jsp" />
</body>
</html>