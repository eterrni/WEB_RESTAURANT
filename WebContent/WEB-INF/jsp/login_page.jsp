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
	
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.login_page.login_message" var="login_message" />
	<fmt:message bundle="${loc}" key="local.login_page.password_message" var="password_message" />
	<fmt:message bundle="${loc}" key="local.button" var="button" />
</head>
<body>

		<form action="Controller" method="post">
		<input type="hidden" name="command" value="login" /> 
		<c:out value="${login_message}" /><br>
		<input type="text" name="login" value="" /><br> 
		<c:out value="${password_message}" /><br>
		<input type="password" name="password" value="" /><br>
		<input type="submit" value="${button}" />
		</form><br>
		
		
		<form action="ServletForChangeLanguage" method="post">
		<input type="hidden" name="local" value="ru" />
		<input type="hidden" name="name_jsp" value="login_page.jsp" />
		<input type="submit" name="local" value="${ru_button}" />
		</form>
	
		<form action="ServletForChangeLanguage" method="post" >
		<input type="hidden" name="local" value="en" />
		<input type="hidden" name="name_jsp" value="login_page.jsp" />
		<input type="submit" name="local" value="${en_button}" />
		</form>
</body>
</html>