<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Update user data</title>
	<style><%@include file="/front/css/reset.css"%></style>
    <style><%@include file="/front/css/core.css"%></style>
    
    <fmt:setLocale value="${sessionScope.locale}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.hello" var="hello" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.order" var="order" />
	<fmt:message bundle="${loc}" key="local.welcome_page.admin_page" var="admin_page" />
	<fmt:message bundle="${loc}" key="local.welcome_page.user_order_page" var="user_order_page" />
	<fmt:message bundle="${loc}" key="local.personal_accout_button" var="personal_accout_button" />
	<fmt:message bundle="${loc}" key="local.personal_logout_button" var="logout_button" />
	<fmt:message bundle="${loc}" key="local.login_button" var="login_button" />
	<fmt:message bundle="${loc}" key="local.registration_button" var="registration_button" />
	
	<fmt:message bundle="${loc}" key="local.update_user_data_page.name" var="update_name" />
	<fmt:message bundle="${loc}" key="local.update_user_data_page.surname" var="update_surname" />
	<fmt:message bundle="${loc}" key="local.update_user_data_page.patronymic" var="update_patronymic" />
	<fmt:message bundle="${loc}" key="local.update_user_data_page.phoneNumber" var="update_phoneNumber" />
	<fmt:message bundle="${loc}" key="local.update_user_data_page.age" var="update_age" />
	<fmt:message bundle="${loc}" key="local.update_user_data_page.email" var="update_email" />
	<fmt:message bundle="${loc}" key="local.update_user_data_page.button" var="button" />
	
	
	<fmt:message bundle="${loc}" key="local.welcome_page.menu" var="menu" />
	<fmt:message bundle="${loc}" key="local.welcome_page.stocks" var="stocks" />
	<fmt:message bundle="${loc}" key="local.welcome_page.about_us" var="about_us" />
	<fmt:message bundle="${loc}" key="local.welcome_page.contacts" var="contacts" />
	<fmt:message bundle="${loc}" key="local.welcome_page.work_time" var="work_time" />
	<fmt:message bundle="${loc}" key="local.welcome_page.address" var="address" />
	<fmt:message bundle="${loc}" key="local.welcome_page.social_networks" var="social_networks" />
	
	
</head>
<body>
		<!-- START HEADER -->
    <header>
        <div class="header-up">
            <div class="registr-box">
                <div class="language">
                
                <form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="ru" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_update_user_data_page" />
 				<button type="submit" >${ru_button}</button>
 				</form>
 				
 				<form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="en" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_update_user_data_page" />
 				<button type="submit" >${en_button}</button>
 				</form>
 				
                </div>
 				 <c:if test="${sessionScope.user != null}">
 				${hello}, ${sessionScope.user.name}
 				<ul>
 				<c:if test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
 				<li><a href="Controller?command=go_to_admin_page">${admin_page}</a></li>
 				</c:if>
 				   <li><a href="Controller?command=go_to_user_order_page">${user_order_page}</a></li>
                   <li><a href="Controller?command=go_to_personal_account_page">${personal_accout_button}</a></li>
                   <li><a href="Controller?command=logout">${logout_button}</a></li>
                   <li><a href="Controller?command=go_to_order_page">${order}</a></li>
                </ul>
 				</c:if>
 				
 				<c:if test="${sessionScope.user == null }">
 				<ul>
                   <li><a href="Controller?command=go_to_registration_page">${registration_button}</a></li>
                    <li><a href="Controller?command=go_to_login_page">${login_button}</a></li>
                </ul>
 				</c:if>
            </div>
        </div>
        <div class="header-down">
            <div class="header-down-box">
                <div class="logo-box" onclick="">
                    <a href="Controller?command=go_to_welcome_page"><img src="https://i.pinimg.com/originals/f6/61/9c/f6619c65315d26e6a2ce19a6a6043257.png" alt="logo"></a>
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
<div class="main2">

		<form action="Controller" method="post" >
		
		<input type="hidden" name="command" value="update_user_data" />
		<input type="hidden" name="id" value="${sessionScope.user.id}" />
		
		${update_name}
        <input type="text" name="name" pattern="[a-zA-Zа-яА-ЯЁё]{3,15}" value="${sessionScope.user.name}" required/><br>
        ${update_surname}
        <input type="text" name="surname" pattern="[a-zA-Zа-яА-ЯЁё]{3,20}" value="${sessionScope.user.surname}" required/><br>
        ${update_patronymic}
        <input type="text" name="patronymic" pattern="[a-zA-Zа-яА-ЯЁё]{3,20}" value="${sessionScope.user.patronymic}" required/><br>
        ${update_phoneNumber}
		<input type="tel" name="phoneNumber" pattern="^[0-9\\(\\)-+\\s]+$" value="${sessionScope.user.phoneNumber}" required/><br>
		${update_age}
        <input type="number" min="6" max="100" name="age" pattern="\d+" value="${sessionScope.user.age}" required/><br>
        ${update_email}
        <input type="email" name="email" pattern="^[\w.-_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}$" value="${sessionScope.user.email}" required/><br>
        
        <input type="submit" value="${button}" />
        </form>
        
</div>
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
                    <a href="http://instagram.com"><img src="front/img/icon-footer-instagram.png" alt="insta" /></a>
                </div>
            </div>
        </div>
    </footer>
<!-- END FOOTER-->
		
</body>
</html>