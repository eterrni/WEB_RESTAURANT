<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<style><%@include file="/front/css/reset.css"%></style>
    <style><%@include file="/front/css/core.css"%></style>
    
    <fmt:setLocale value="${sessionScope.locale}" />
    
    <fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
    
    <fmt:message bundle="${loc}" key="local.hello" var="hello" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.my_data" var="my_data" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.name" var="name" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.surname" var="surname" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.patronymic" var="patronymic" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.login" var="login" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.phone_number" var="phoneNumber" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.age" var="age" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.email" var="email" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.status" var="status" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.id" var="id" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.my_data" var="my_data" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.button" var="button" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.successful_update" var="successful_update" />
    <fmt:message bundle="${loc}" key ="local.personal_account_page.unsuccessful_update" var="unsuccessful_update" />
    
    
    <fmt:message bundle="${loc}" key="local.welcome_page.menu" var="menu" />
	<fmt:message bundle="${loc}" key="local.welcome_page.stocks" var="stocks" />
	<fmt:message bundle="${loc}" key="local.welcome_page.about_us" var="about_us" />
	<fmt:message bundle="${loc}" key="local.welcome_page.contacts" var="contacts" />
	<fmt:message bundle="${loc}" key="local.welcome_page.work_time" var="work_time" />
	<fmt:message bundle="${loc}" key="local.welcome_page.address" var="address" />
	<fmt:message bundle="${loc}" key="local.welcome_page.social_networks" var="social_networks" />
	
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.welcome_page.user_payment_page" var="user_payment_page" />
	<fmt:message bundle="${loc}" key="local.welcome_page.admin_page" var="admin_page" />
	<fmt:message bundle="${loc}" key="local.welcome_page.user_order_page" var="user_order_page" />
	<fmt:message bundle="${loc}" key="local.locbutton.order" var="order" />
	<fmt:message bundle="${loc}" key="local.personal_accout_button" var="personal_accout_button" />
	<fmt:message bundle="${loc}" key="local.personal_logout_button" var="logout_button" />
    
</head>
<body>
<!-- START HEADER -->
    <header>
        <div class="header-up">
            <div class="registr-box">
                <div class="language">
                
                <form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="ru" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_personal_account_page" />
 				<button type="submit" >${ru_button}</button>
 				</form>
 				
 				<form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="en" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_personal_account_page" />
 				<button type="submit" >${en_button}</button>
 				</form>
                </div>
 				<ul>
 				<li style="margin-right: 20px;">${hello}, ${sessionScope.user.name}</li>
 				<c:if test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
 				<li><a href="Controller?command=go_to_admin_page">${admin_page}</a></li>
 				</c:if>
 				<li><a href="Controller?command=go_to_user_payment_page">${user_payment_page}</a></li>
 				   <li><a href="Controller?command=go_to_user_order_page">${user_order_page}</a></li>
                   <li><a href="Controller?command=go_to_personal_account_page">${personal_accout_button}</a></li>
                   <li><a href="Controller?command=logout">${logout_button}</a></li>
                   <li><a href="Controller?command=go_to_order_page">${order}</a></li>
                </ul>
 				
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
<main>
<div class="myData-title">
            <p>${my_data}</p>
</div>
<div class="myData-wrapper">
       <div class="myData-box">
               <div class="box-name">
                    <p>${name}</p>
               </div>
               <div class="box-content">
                    <p>${sessionScope.user.name}</p>
               </div>
       </div>
            <div class="myData-box">
                <div class="box-name">
                    <p>${surname}</p>
                </div>
                <div class="box-content">
                    <p>${sessionScope.user.surname}</p>
                </div>
            </div>
            <div class="myData-box">
                <div class="box-name">
                    <p>${patronymic}</p>
                </div>
                <div class="box-content">
                    <p>${sessionScope.user.patronymic}</p>
                </div>
            </div>
            <div class="myData-box">
                <div class="box-name">
                    <p>${phoneNumber}</p>
                </div>
                <div class="box-content">
                    <p>${sessionScope.user.phoneNumber}</p>
                </div>
            </div>
            <div class="myData-box">
                <div class="box-name">
                    <p>${age}</p>
                </div>
                <div class="box-content">
                    <p>${sessionScope.user.age}</p>
                </div>
            </div>
            <div class="myData-box">
                <div class="box-name">
                    <p>${email}</p>
                </div>
                <div class="box-content">
                    <p>${sessionScope.user.email}</p>
                </div>
            </div>
            <div class="myData-box">
                <div class="box-name">
                    <p>${id}</p>
                </div>
                <div class="box-content">
                    <p>${sessionScope.user.id}</p>
                </div>
            </div>
        </div>
        <div class="change-data">
		<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_update_user_data_page" />
		<input type="submit" class="change-data-button" value="${button}" />
		</form>
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
                    <a href="http://instagram.com"><img src="front/img/icon-footer-instagram.png" alt="insta" /></a>
                </div>
            </div>
        </div>
        <p class="copyright-text"><mytag:copyright copyrightText="Copyright 2020"/></p>
    </footer>
<!-- END FOOTER-->
</body>
</html>