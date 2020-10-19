	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style><%@include file="/front/css/reset.css"%></style>
    <style><%@include file="/front/css/core.css"%></style>


    <fmt:setLocale value="${sessionScope.locale}"/>
	
	<fmt:setBundle basename="by.epamtc.restaurant.localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.hello" var="hello" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.order" var="order" />
	<fmt:message bundle="${loc}" key="local.login_button" var="login_button" />
	<fmt:message bundle="${loc}" key="local.registration_button" var="registration_button" />
	<fmt:message bundle="${loc}" key="local.personal_accout_button" var="personal_accout_button" />
	<fmt:message bundle="${loc}" key="local.personal_logout_button" var="logout_button" />
	
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
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_menu_page" />
 				<button type="submit" >${ru_button}</button>
 				</form>
 				
 				<form action="ServletForChangeLanguage" method="post">
 				<input type="hidden" name="locale" value="en" />
 				<input type="hidden" name="previousRequest" value="Controller?command=go_to_menu_page" />
 				<button type="submit" >${en_button}</button>
 				</form>
 				
                </div>
 				 <c:if test="${sessionScope.user != null}">
 				${hello}, ${sessionScope.user.name}
 				<ul>
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
	<main>

<div class="contant-wrapper">
		<c:forEach var="dish" items="${dishList}" >
            <div class="box">
	                <div class="box-img"> 
	                    <img src="https://i.artfile.ru/4200x2880_896995_[www.ArtFile.ru].jpg" alt="image">
	                </div>
	                <div class="box-text">
	                    <div class="name"><p><c:out value="${dish.name}" /></p></div>
	                    <div class="item-opis"><p><c:out value="${dish.description}" /></p></div>
	                    <div class="price"><c:out value="${dish.price}" /></div>
	                    <c:if test="${sessionScope.user != null}">
	                    <div class="box-button">
	                   	 <form action="Controller" method="post">
	       					<input type="hidden" name="command" value="add_dish_to_order" />
	        				<input type="hidden" name="id" value="${dish.id}" />
	       			   	    <input type="submit" value="Добавить в заказ" />
	       				 </form>
	       			    </div>
	        			</c:if>
	                </div>
            </div>
        </c:forEach>
       
	 <c:forEach var="drink" items="${drinkList}" >
            <div class="box">
	                <div class="box-img"> 
	                    <img src="https://i.artfile.ru/4200x2880_896995_[www.ArtFile.ru].jpg" alt="image">
	                </div>
	                <div class="box-text">
	                    <div class="name"><p><c:out value="${drink.name}" /></p></div>
	                    <div class="item-opis"><p><c:out value="${drink.description}" /></p></div>
	                    <div class="price"><c:out value="${drink.price}" /></div>
	                    <c:if test="${sessionScope.user != null}">
	                    <div class="box-button">
	                   	 <form action="Controller" method="post">
	       					<input type="hidden" name="command" value="add_drink_to_order" />
	        				<input type="hidden" name="id" value="${drink.id}" />
	       			   	    <input type="submit" value="Добавить в заказ" />
	       				 </form>
	       			    </div>
	        			</c:if>
	                </div>
            </div>
     </c:forEach>

	 <c:forEach var="desert" items="${desertList}" >
            <div class="box">
	                <div class="box-img"> 
	                    <img src="https://i.artfile.ru/4200x2880_896995_[www.ArtFile.ru].jpg" alt="image">
	                </div>
	                <div class="box-text">
	                    <div class="name"><p><c:out value="${desert.name}" /></p></div>
	                    <div class="item-opis"><p><c:out value="${desert.description}" /></p></div>
	                    <div class="price"><c:out value="${desert.price}" /></div>
	                    <c:if test="${sessionScope.user != null}">
	                    <div class="box-button">
	                   	 <form action="Controller" method="post">
	       					<input type="hidden" name="command" value="add_desert_to_order" />
	        				<input type="hidden" name="id" value="${desert.id}" />
	       			   	    <input type="submit" value="Добавить в заказ" />
	       				 </form>
	       			    </div>
	        			</c:if>
	                </div>
            </div>
     </c:forEach>
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
    </footer>
<!-- END FOOTER-->
		
</body>
</html>