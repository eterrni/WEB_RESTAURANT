<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<style><%@include file="/WEB-INF/css/reset.css"%></style>
    <style><%@include file="/WEB-INF/css/core.css"%></style>
    
    <fmt:setLocale value="${sessionScope.locale}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.login_button" var="login_button" />
	<fmt:message bundle="${loc}" key="local.registration_button" var="registration_button" />
	
	<fmt:message bundle="${loc}" key="local.welcome_page.menu" var="menu" />
	<fmt:message bundle="${loc}" key="local.welcome_page.stocks" var="stocks" />
	<fmt:message bundle="${loc}" key="local.welcome_page.about_us" var="about_us" />
	<fmt:message bundle="${loc}" key="local.welcome_page.contacts" var="contacts" />
	<fmt:message bundle="${loc}" key="local.welcome_page.work_time" var="work_time" />
	<fmt:message bundle="${loc}" key="local.welcome_page.address" var="address" />
	<fmt:message bundle="${loc}" key="local.welcome_page.social_networks" var="social_networks" />
	
	<fmt:message bundle="${loc}" key="local.login_page.login_message" var="login_message" />
	<fmt:message bundle="${loc}" key="local.login_page.password_message" var="password_message" />
	<fmt:message bundle="${loc}" key="local.button" var="button" />
	
</head>
<body>
<!-- START HEADER -->
    <header>
        <div class="header-up">
            <div class="registr-box">
                <div class="language">
                
                <form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="ru" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_login_page" />
 				<button type="submit" >${ru_button}</button>
 				</form>
 				
 				<form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="en" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_login_page" />
 				<button type="submit" >${en_button}</button>
 				</form>
                </div>
 				<ul>
                   <li><a href="Controller?command=go_to_registration_page">${registration_button}</a></li>
                    <li><a href="Controller?command=go_to_login_page">${login_button}</a></li>
                </ul>
            </div>
        </div>
        <div class="header-down">
            <div class="header-down-box">
                <div class="logo-box" onclick="">
                    <img src="https://i.pinimg.com/originals/f6/61/9c/f6619c65315d26e6a2ce19a6a6043257.png" alt="logo">
                </div>
                <div class="menu">
                    <ul>
                        <li><a href="Controller?command=go_to_menu_page">${menu}</a></li>
                        <li><a href="Controller?command=go_to_stock_page">${stocks}</a></li>
                        <li><a href="Controller?command=go_to_about_us_page">${about_us}</a></li>
                        <li><a href="Controller?command=go_to_contact_page">${contacts}</a></li>
                    </ul>
                </div>           
            </div> 
        </div>
    </header>
<!-- END HEADER -->
	<main>
        <div class="login-wrapper">
            <div class="enter-account">
                <form action="Controller" method="post">
				<input type="hidden" name="command" value="login" /> 
				<input type="text" name="login" value="" placeholder="${login_message}" /><br> 
				<input type="password" name="password" value="" placeholder="${password_message}"/><br>
				<input type="submit" value="${button}" />
				</form>
				
				<c:if test="${sessionScope.authorization_message eq 'incorrect_data'}">
          		<p style="color:red">Incorrect data</p>
            	</c:if>
            	
            	<c:if test="${sessionScope.authorization_message eq 'user_not_exist'}">
          		<p style="color:red">Such a user is not registered</p>
            	</c:if>
            	
            </div>
        </div>
    </main>
		<!-- START FOOTER-->
    <footer>
        <div class="footer-box">
            <div class="time-work">
                <p>${work_time}</p>
            </div>
            <div class="our-contacts">
                <p>${address}</p>
            </div>
            <div class="how-to-pay">
                <p>${social_networks}</p>
                <div class="social-logo">
                    <img src="/img/icon-footer-instagram.png" alt="insta">
                </div>
            </div>
        </div>
    </footer>
<!-- END FOOTER-->
		
</body>
</html>