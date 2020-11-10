<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order page</title>
	<style><%@include file="/front/css/reset.css"%></style>
    <style><%@include file="/front/css/core.css"%></style>
    
    <fmt:setLocale value="${sessionScope.locale}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.hello" var="hello" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.welcome_page.user_payment_page" var="user_payment_page" />
	<fmt:message bundle="${loc}" key="local.welcome_page.admin_page" var="admin_page" />
	<fmt:message bundle="${loc}" key="local.welcome_page.user_order_page" var="user_order_page" />
	<fmt:message bundle="${loc}" key="local.locbutton.order" var="order" />
	<fmt:message bundle="${loc}" key="local.login_button" var="login_button" />
	<fmt:message bundle="${loc}" key="local.registration_button" var="registration_button" />
	<fmt:message bundle="${loc}" key="local.personal_accout_button" var="personal_accout_button" />
	<fmt:message bundle="${loc}" key="local.personal_logout_button" var="logout_button" />
	
	<fmt:message bundle="${loc}" key="local.order_page.order_list" var="order_list" />
	<fmt:message bundle="${loc}" key="local.order_page.button" var="button" />
	<fmt:message bundle="${loc}" key="local.order_page.goods_name" var="goods_name" />
	<fmt:message bundle="${loc}" key="local.order_page.goods_price" var="goods_price" />
	<fmt:message bundle="${loc}" key="local.order_page.remove_goods_from_order_button" var="remove_goods_from_order_button" />
	<fmt:message bundle="${loc}" key="local.order_page.empty_order_message_1" var="empty_order_message_1" />
	<fmt:message bundle="${loc}" key="local.order_page.empty_order_message_2" var="empty_order_message_2" />
	
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
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_order_page" />
 				<button type="submit" >${ru_button}</button>
 				</form>
 				
 				<form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="en" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_order_page" />
 				<button type="submit" >${en_button}</button>
 				</form>
 				
                </div>
 				<c:if test="${sessionScope.user != null}">
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
 				</c:if>
 				
 				<c:if test="${sessionScope.user == null}">
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
<main>
<c:if test="${sessionScope.order != null}" >
        <div class="order-list">
            <div class="order-list-title">
                <p>${order_list}</p>
            </div>
            <div class="box-wrap">
                <div class="name-box">
                    <p>${goods_name}</p>
                </div>
                <div class="price-box">
                    <p>${goods_price}</p>
                </div>
            </div>
            <c:forEach var="goods" items="${sessionScope.order.orderList}" >
            <div class="order-list-box">
                <div class="name-food">
                    <p>${goods.name}</p>
                </div>
                <div class="price-food">
                    <p>${goods.price}</p>
                </div>
                <div class="delete-button">
                    	<form action="Controller" method="post">
						<input type="hidden" name="command" value="remove_goods_from_order" />
						<input type="hidden" name="remove_goods_name" value="${goods.name}" />
						<input type="submit" class="button" value="${remove_goods_from_order_button}" />
			</form>
                </div>
            </div>
	</c:forEach>
            <div class="arrange-order">
                <form action="Controller" method ="post">
       		<input type="hidden" name="command" value="place_order" />
        	<input type="submit" class="arrange-order-button" value="${button}" />
       		</form>
            </div>
        </div>
</c:if>

<c:if test = "${sessionScope.order == null}">
<h1>${empty_order_message_1} <a href="Controller?command=go_to_menu_page">${empty_order_message_2}</a></h1>
</c:if>
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