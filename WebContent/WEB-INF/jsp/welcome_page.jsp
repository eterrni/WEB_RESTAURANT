<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

	<fmt:setLocale value="${sessionScope.local}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.welcom_page.message1" var="message1" />
	<fmt:message bundle="${loc}" key="local.welcom_page.message2" var="message2" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.loginbutton" var="login_button" />
	<fmt:message bundle="${loc}" key="local.registrationbutton" var="registration_button" />
</head>
<body>
		<h1><c:out value="${message1}" /></h1>
		<h1><c:out value="${message2}" /></h1>
		<form action="goToTransferServlet" method="post">
		<input type="hidden" name="command" value="login" />
		<input type="submit" value="${login_button}" />
		</form>
		<form action="goToTransferServlet" method="post">
		<input type="hidden" name="command" value="registration" />
		<input type="submit" value="${registration_button}" />
		</form>
		<br>
		
	<form action="ServletForChangeLanguage" method="post">
	<input type="hidden" name="local" value="ru" />
	<input type="hidden" name="name_jsp" value="welcome_page.jsp" />
	<input type="submit" value="${ru_button}" />
	</form>
	
	<form action="ServletForChangeLanguage" method="post" >
	<input type="hidden" name="local" value="en" />
	<input type="hidden" name="name_jsp" value="welcome_page.jsp" />
	<input type="submit" value="${en_button}" />
	</form>
	
</body>
</html>