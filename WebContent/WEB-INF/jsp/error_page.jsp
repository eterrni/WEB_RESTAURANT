<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Error page</title>
	<fmt:setLocale value="${sessionScope.locale}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	
	<fmt:message bundle="${loc}" key="local.error_page.message" var="message" />
	<fmt:message bundle="${loc}" key="local.error_page.error_description" var="description" />
</head>
<body>
		<h1>${message}</h1>
		<h3>${description}</h3>
		<p>${requestScope.error.message}</p>
		<p>${requestScope.error.stackTrace}</p>
		
		<form action="ServletForChangeLanguage" method="post">
		<input type="hidden" name="locale" value="ru" />
		<input type="hidden" name="name_jsp" value="error_page.jsp" />
		<input type="submit" name="local" value="${ru_button}" />
		</form>
	
		<form action="ServletForChangeLanguage" method="post" >
		<input type="hidden" name="locale" value="en" />
		<input type="hidden" name="name_jsp" value="error_page.jsp" />
		<input type="submit" name="local" value="${en_button}" />
		</form>
</body>
</html>