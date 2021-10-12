<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<jsp:include page="components/header.jsp" />
		<div class="display-4 text-center mb-4"><fmt:message key="calculator.title" /></div>
	<c:if test="${requestScope.best_plan == null}">
		
	<div class="text text-center lh-sm mb-2 col-sm-7 fst-italic fw-light mx-auto"><fmt:message key="calculator.lead" /></div>

	<div class="row align-content-center mx-auto fw-light flex-grow-1 ">

		<form method="POST" action="controller?command=calculator">
			<table class="table table-hover">
			<thead>
			<tr><th><fmt:message key="calculator.per.month" /></th></tr>
			</thead>
			<tbody>
			<tr><td>
			<b><fmt:message key="calculator.calls.within.network" /></b>
			<div >
				<input class="form-check-input" type="radio" name="within_network" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 1-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
				<input class="form-check-input" type="radio" name="within_network" id="radios5" required="required" value="0"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.not.use" /></label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.calls.other.networks" /></b>
			<div >
				<input class="form-check-input" type="radio" name="other_networks" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 1-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios5" required="required" value="0"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.not.use" /></label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.calls.abroad" /></b>
			<div >
				<input class="form-check-input" type="radio" name="abroad" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 1-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
				<input class="form-check-input" type="radio" name="abroad" id="radios5" required="required" value="0"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.not.use" /></label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.calls.videocall" /></b>
			<div >
				<input class="form-check-input" type="radio" name="videocall" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 1-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
				<input class="form-check-input" type="radio" name="videocall" id="radios5" required="required" value="0"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.not.use" /></label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.internet" /></b>
			<div >
				<input class="form-check-input" type="radio" name="internet" id="radios1" required="required" value="500"> 
				<label class="form-check-label" for="radios1"><fmt:message key="calculator.no.more.than" /> 500 <fmt:message key="calculator.mb" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios2" required="required" value="1024"> 
				<label class="form-check-label" for="radios2"><fmt:message key="calculator.no.more.than" /> 1 <fmt:message key="calculator.gb" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios3" required="required" value="2048"> 
				<label class="form-check-label" for="radios3"><fmt:message key="calculator.no.more.than" /> 2 <fmt:message key="calculator.gb" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios4" required="required" value="5120"> 
				<label class="form-check-label" for="radios4"><fmt:message key="calculator.no.more.than" /> 5 <fmt:message key="calculator.gb" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios5" required="required" value="10240"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.more.than" /> 5 <fmt:message key="calculator.gb" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios5" required="required" value="0"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.not.use" /></label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.sms" /></b>
			<div >
				<input class="form-check-input" type="radio" name="sms" id="radios1" required="required" value="5"> 
				<label class="form-check-label" for="radios1"> 1-10  <fmt:message key="calculator.sms" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios2" required="required" value="15"> 
				<label class="form-check-label" for="radios2"> 11-20  <fmt:message key="calculator.sms" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios3" required="required" value="35"> 
				<label class="form-check-label" for="radios3"> 21-50  <fmt:message key="calculator.sms" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios6" required="required" value="75"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" />  50 <fmt:message key="calculator.sms" /> </label>
				<input class="form-check-input" type="radio" name="sms" id="radios5" required="required" value="0"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.not.use" /></label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.mms" /></b>
			<div >
				<input class="form-check-input" type="radio" name="mms" id="radios1" required="required" value="5"> 
				<label class="form-check-label" for="radios1"> 1-10 <fmt:message key="calculator.mms" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios2" required="required" value="15"> 
				<label class="form-check-label" for="radios2"> 11-20 <fmt:message key="calculator.mms" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios3" required="required" value="35"> 
				<label class="form-check-label" for="radios3"> 21-50 <fmt:message key="calculator.mms" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 50 <fmt:message key="calculator.mms" /> </label>
				<input class="form-check-input" type="radio" name="mms" id="radios5" required="required" value="0"> 
				<label class="form-check-label" for="radios5"><fmt:message key="calculator.not.use" /></label>
			</div>
			</td></tr>
			</tbody>
			</table>
			
			<button type="submit" class="btn btn-outline-primary btn-sm"><fmt:message key="calculator.suggest.tariff" /></button>
		</form>
	</div>
	</c:if>

	<c:if test="${requestScope.best_plan != null}">
		<div
			class="container tab-content tab-pane align-content-center mx-auto fade show active flex-grow-1"
			id="monthly">
			<div
				class="text text-center fs-3 fw-light lh-sm col-sm-9 mb-3 mx-auto">
				<fmt:message key="calculator.result.lead" />
			</div>

			<div
				class="container align-content-center col-xs-6 col-sm-6 col-md-4 col-lg-4 col-xl-4 mb-3 mb-md-4">


				<div class="card align-content-center text-center mb-5 h-100 ">
					<div class="card-body d-flex flex-column">
						<div class="mb-4">
							<h3>${requestScope.best_plan.name}</h3>
							<span class="display-4">${requestScope.best_plan.regularPayment/100}</span>
							<br/> <span class="display-6"><fmt:message
									key="calculator.result.rub.per.month" /></span>
							<p class="lead text-center mb-4">
								<fmt:message key="calculator.result.regular.payment" />
							</p>
						</div>

						<div class="mb-4">
							<p class="lead text-center mb-4">${requestScope.best_plan.description}</p>
						</div>

						<div class="mt-auto">
							<a
								href="${pageContext.request.contextPath}/controller?command=show_full_plan&id=${requestScope.best_plan.id}"
								class="btn btn-outline-primary"><fmt:message
									key="calculator.result.learn.more" /></a>
						</div>
					</div>
				</div>
				<div class="container text-center ">
					<a
						href="${pageContext.request.contextPath}/controller?command=go_to_calculator_page"
						class="btn btn-outline-dark"><fmt:message
							key="calculator.result.try.again" /></a>
				</div>
			</div>
		</div>
	</c:if>
	<br/>
	<jsp:include page="components/footer.jsp" />


</body>
</html>