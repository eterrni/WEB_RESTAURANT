<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Document</title>
    	<style><%@include file="/WEB-INF/css/reset.css"%></style>
    	<style><%@include file="/WEB-INF/css/core.css"%></style>
    	<style><%@include file="/WEB-INF/css/registration.css"%></style>

   	<fmt:setLocale value="${sessionScope.locale}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.registration.button" var="button" />
	<fmt:message bundle="${loc}" key="local.registration_page.name" var="name" />
	<fmt:message bundle="${loc}" key="local.registration_page.surname" var="surname" />
	<fmt:message bundle="${loc}" key="local.registration_page.patronymic" var="patronymic" />
	<fmt:message bundle="${loc}" key="local.registration_page.login" var="login" />
	<fmt:message bundle="${loc}" key="local.registration_page.password" var="password" />
	<fmt:message bundle="${loc}" key="local.registration_page.phone_number" var="phone_number" />
	<fmt:message bundle="${loc}" key="local.registration_page.age" var="age" />
	<fmt:message bundle="${loc}" key="local.registration_page.email" var="email" />
	
	<fmt:message bundle="${loc}" key="local.welcome_page.menu" var="menu" />
	<fmt:message bundle="${loc}" key="local.welcome_page.stocks" var="stocks" />
	<fmt:message bundle="${loc}" key="local.welcome_page.about_us" var="about_us" />
	<fmt:message bundle="${loc}" key="local.welcome_page.contacts" var="contacts" />
	<fmt:message bundle="${loc}" key="local.welcome_page.work_time" var="work_time" />
	<fmt:message bundle="${loc}" key="local.welcome_page.address" var="address" />
	<fmt:message bundle="${loc}" key="local.welcome_page.social_networks" var="social_networks" />
	<fmt:message bundle="${loc}" key="local.registration_page.registration_unsuccessful" var="registration_unsuccessful" />
	<fmt:message bundle="${loc}" key="local.registration_page.registration_successful_1" var="registration_successful_1" />
	<fmt:message bundle="${loc}" key="local.registration_page.registration_successful_2" var="registration_successful_2" />
	<fmt:message bundle="${loc}" key="local.registration_page.registration_successful_3" var="registration_successful_3" />
	<fmt:message bundle="${loc}" key="local.registration_page.registration_successful_4" var="registration_successful_4" />
	<fmt:message bundle="${loc}" key="local.registration_page.user_already_exists" var="user_already_exists" />
</head>
<body>
    <header>
        <div class="header-up">
            <div class="registr-box">
                <div class="language">
                
            <form action="ServletForChangeLanguage" method="post">
 			<input type="hidden" name="locale" value="ru" />
 			<input type="hidden" name="name_jsp" value="registration_page.jsp" />
 			<button type="submit" >${ru_button}</button>
 			</form>
 				
 			<form action="ServletForChangeLanguage" method="post">
 			<input type="hidden" name="locale" value="en" />
 			<input type="hidden" name="name_jsp" value="registration_page.jsp" />
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
    <div class="main-form">
        <div class="form-wrapper">
            <form action="Controller" method="GET">
                <div class="registr-form">
					<input type="hidden" name="command" value="registration" />
                    <input type="text" name="name" pattern="[a-zA-Zа-яА-ЯЁё]{3,15}" placeholder="${name}"/>
                    <input type="text" name="surname" pattern="[a-zA-Zа-яА-ЯЁё]{3,20}" placeholder="${surname}"/>
                    <input type="text" name="patronymic" pattern="[a-zA-Zа-яА-ЯЁё]{3,20}" placeholder="${patronymic}"/>
                    <input type="text" name="login" pattern="[a-zA-Z][a-zA-Z0-9]{2,14}" placeholder="${login}">
                    <input type="password" name="password" pattern="((?=.*\d)(?=.*[a-zA-Z]).{5,15})" placeholder="${password}"/>
		  		    <input type="tel" name="phoneNumber" placeholder="${phone_number}"/><br>
                    <input type="number" min="6" max="100" name="age" pattern="\d+" placeholder="${age}" /><br>
                    <input type="email" name="email" placeholder="${email}" />
                </div>
                <div class="button">
                    <input type="submit" value="${button}" />
                </div>
            </form>
            
            <c:if test="${sessionScope.registration_message eq 'registration_successful'}">
          		<h3>${registration_successful_1}</h3>
                <p>${registration_successful_2} <a href="Controller?command=go_to_login_page">${registration_successful_3}</a> ${registration_successful_4}</p>
            </c:if>
            
            <c:if test="${sessionScope.registration_message eq 'registration_unsuccessful'}">
                <p>${registartion_unsuccessful}</p>
            </c:if>
            
            <c:if test="${sessionScope.registration_message eq 'user_exist'}">
                <p>${user_already_exists}</p>
            </c:if>
            
        </div>
    </div>
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
</body>
</html>