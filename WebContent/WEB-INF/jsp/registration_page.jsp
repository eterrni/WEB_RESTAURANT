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
	<fmt:message bundle="${loc}" key="local.button" var="button" />
	<fmt:message bundle="${loc}" key="local.registration_page.name" var="name" />
	<fmt:message bundle="${loc}" key="local.registration_page.surname" var="surname" />
	<fmt:message bundle="${loc}" key="local.registration_page.patronymic" var="patronymic" />
	<fmt:message bundle="${loc}" key="local.registration_page.login" var="login" />
	<fmt:message bundle="${loc}" key="local.registration_page.password" var="password" />
	<fmt:message bundle="${loc}" key="local.registration_page.phone_number" var="phone_number" />
	<fmt:message bundle="${loc}" key="local.registration_page.age" var="age" />
	<fmt:message bundle="${loc}" key="local.registration_page.email" var="email" />
</head>
<body>
		<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<c:out value="${name}" /><br>
		<input type="text" name="name" value="" /><br>
		<c:out value="${surname}" /><br>
		<input type="text" name="surname" value="" /><br>
		<c:out value="${patronymic}" /><br>
		<input type="text" name="patronymic" value="" /><br>
		<c:out value="${login}" /><br>
		<input type="text" name="login" value="" /><br>
		<c:out value="${password}" /><br>
		<input type="password" name="password" value="" /><br>
		<c:out value="${phone_number}" /><br>
		<input type="text" name="phone_number" value="" /><br>
		<c:out value="${age}" /><br>
		<input type="text" name="age" value="" /><br>
		<c:out value="${email}" /><br>
		<input type="text" name="email" value="" /><br>
		<input type="submit" value="${button}" />
		</form><br>
		
		<form action="ServletForChangeLanguage" method="post">
		<input type="hidden" name="local" value="ru" />
		<input type="hidden" name="name_jsp" value="registration_page.jsp" />
		<input type="submit" name="local" value="${ru_button}" />
		</form>
	
		<form action="ServletForChangeLanguage" method="post" >
		<input type="hidden" name="local" value="en" />
		<input type="hidden" name="name_jsp" value="registration_page.jsp" />
		<input type="submit" name="local" value="${en_button}" />
		</form>
</body>
</html>