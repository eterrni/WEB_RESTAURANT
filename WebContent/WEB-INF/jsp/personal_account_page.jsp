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
    
    <fmt:setLocale value="${sessionScope.locale}" />
    
    <fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
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
    
    
    <fmt:message bundle="${loc}" key="local.welcome_page.menu" var="menu" />
	<fmt:message bundle="${loc}" key="local.welcome_page.stocks" var="stocks" />
	<fmt:message bundle="${loc}" key="local.welcome_page.about_us" var="about_us" />
	<fmt:message bundle="${loc}" key="local.welcome_page.contacts" var="contacts" />
	<fmt:message bundle="${loc}" key="local.welcome_page.work_time" var="work_time" />
	<fmt:message bundle="${loc}" key="local.welcome_page.address" var="address" />
	<fmt:message bundle="${loc}" key="local.welcome_page.social_networks" var="social_networks" />
	
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
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
 				Hello, ${sessionScope.user.name}
 				<ul>
                   <li><a href="Controller?command=go_to_personal_account_page">${personal_accout_button}</a></li>
                   <li><a href="Controller?command=logout">${logout_button}</a></li>
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
		<h4>${my_data}</h4>
		<p>${name}: ${sessionScope.user.name}</p>
		<p>${surname}: ${sessionScope.user.surname}</p>
		<p>${patronymic}: ${sessionScope.user.patronymic}</p>
		<p>${phoneNumber}: ${sessionScope.user.phoneNumber}</p>
		<p>${age}: ${sessionScope.user.age}</p>
		<p>${email}: ${sessionScope.user.email}</p>
		<p>${status}: ${sessionScope.user.role}</p>
		<p>${id}: ${sessionScope.user.id}</p>
		
		<a href="Controller?command=go_to_update_user_data_page">Change the data</a><br>
		
		<c:if test="${sessionScope.update_message eq 'successful_update_data'}">
          <p style="color:green">Successful update data!</p>
        </c:if>
            
        <c:if test="${sessionScope.update_message eq 'unsuccessful_update_data'}">
          <p style="color:red">Unsuccessful update data!</p>
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
                    <img src="/img/icon-footer-instagram.png" alt="insta">
                </div>
            </div>
        </div>
    </footer>
<!-- END FOOTER-->
</body>
</html>