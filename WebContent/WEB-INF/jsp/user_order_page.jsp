<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User order</title>
	<style><%@include file="/front/css/reset.css"%></style>
    <style><%@include file="/front/css/core.css"%></style>
    
    <fmt:setLocale value="${sessionScope.locale}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.hello" var="hello" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.order" var="order" />
	<fmt:message bundle="${loc}" key="local.login_button" var="login_button" />
	<fmt:message bundle="${loc}" key="local.welcome_page.user_payment_page" var="user_payment_page" />
	<fmt:message bundle="${loc}" key="local.welcome_page.admin_page" var="admin_page" />
	<fmt:message bundle="${loc}" key="local.welcome_page.user_order_page" var="user_order_page" />
	<fmt:message bundle="${loc}" key="local.registration_button" var="registration_button" />
	<fmt:message bundle="${loc}" key="local.personal_accout_button" var="personal_accout_button" />
	<fmt:message bundle="${loc}" key="local.personal_logout_button" var="logout_button" />
	
	<fmt:message bundle="${loc}" key="local.user_order_page.order_id" var="order_id" />
	<fmt:message bundle="${loc}" key="local.user_order_page.order_date" var="order_date" />
	<fmt:message bundle="${loc}" key="local.user_order_page.order_status" var="order_status" />
	<fmt:message bundle="${loc}" key="local.user_order_page.my_orders" var="my_orders" />
	<fmt:message bundle="${loc}" key="local.user_order_page.detail_order_button" var="detail_order_button" />
	
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
						<input type="hidden" name="locale" value="ru" /> <input
							type="hidden" name="previousRequest"
							value="Controller?command=go_to_user_order_page" />
						<button type="submit">${ru_button}</button>
					</form>

					<form action="ServletForChangeLanguage" method="post">
						<input type="hidden" name="locale" value="en" /> <input
							type="hidden" name="previousRequest"
							value="Controller?command=go_to_user_order_page" />
						<button type="submit">${en_button}</button>
					</form>

				</div>
				<ul>
					<li style="margin-right: 20px;">${hello},
						${sessionScope.user.name}</li>
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
					<a href="Controller?command=go_to_welcome_page"><img
						src="https://i.pinimg.com/originals/f6/61/9c/f6619c65315d26e6a2ce19a6a6043257.png"
						alt="logo"></a>
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
			<p class="user_order-title">${my_orders}</p>
			<div class="user_order-name-wrap">
				<div>${order_id}</div>
				<div>${order_date}</div>
				<div>${order_status}</div>
				<div></div>
			</div>
			<c:forEach var="order" items="${sessionScope.user_order_list}">
				<div class="user_order-contant-wrap">
					<div>${order.id}</div>
					<div>${order.createDate}</div>
					<div>
						<c:if test="${order.status eq 'CONFIRMED'}">
							<p style="color: green">${order.status}</p>
						</c:if>

						<c:if test="${order.status eq 'NOT_CONFIRMED'}">
							<p style="color: red">${order.status}</p>
						</c:if>
					</div>
					<div>
						<form action="Controller" method="post">
							<input type="hidden" name="order_id" value="${order.id}" /> <input
								type="hidden" name="command" value="print_order_detail" /> <input
								type="submit" class="user_order-button"
								value="${detail_order_button}" />
						</form>
					</div>
				</div>
			</c:forEach>

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
					<a href="http://instagram.com"><img
						src="front/img/icon-footer-instagram.png" alt="insta" /></a>
				</div>
			</div>
		</div>
		<p class="copyright-text">
			<mytag:copyright copyrightText="Copyright 2020" />
		</p>
	</footer>
	<!-- END FOOTER-->
</body>
</html>