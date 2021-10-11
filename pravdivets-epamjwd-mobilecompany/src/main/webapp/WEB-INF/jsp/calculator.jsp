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
	<div class="text text-center lh-sm mb-4 col-sm-6 fst-italic fw-light mx-auto"><fmt:message key="calculator.lead" /></div>

	<div class="row align-content-center mx-auto fw-light flex-grow-1 ">

		<form method="POST" action="controller?command=calculator">
			<table class="table table-hover">
			<tbody>
			<tr><td>
			<b><fmt:message key="calculator.calls.within.network" /></b>
			<div >
				<input class="form-check-input" type="radio" name="within_network" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
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
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.calls.other.networks" /></b>
			<div >
				<input class="form-check-input" type="radio" name="other_networks" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
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
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.calls.abroad" /></b>
			<div >
				<input class="form-check-input" type="radio" name="abroad" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
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
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.calls.videocall" /></b>
			<div >
				<input class="form-check-input" type="radio" name="videocall" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
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
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.sms" /></b>
			<div >
				<input class="form-check-input" type="radio" name="sms" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.mms" /></b>
			<div >
				<input class="form-check-input" type="radio" name="mms" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			</td></tr>
			<tr><td>
			<b><fmt:message key="calculator.internet" /></b>
			<div >
				<input class="form-check-input" type="radio" name="internet" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			</td></tr>
			</tbody>
			</table>
			
			<button type="submit" class="btn btn-outline-primary btn-sm"><fmt:message key="calculator.suggest.tariff" /></button>
		</form>
	</div>
<br/>
	<jsp:include page="components/footer.jsp" />


</body>
</html>