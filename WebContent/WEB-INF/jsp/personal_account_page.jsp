<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
		<h1>Мои данные</h1>
		Имя: ${sessionScope.user.name}<br>
		Фамилия: ${sessionScope.user.surname}<br>
		Отчество: ${sessionScope.user.patronymic}<br>
		Логин: ${sessionScope.user.login}<br>
		Номер телефона: ${sessionScope.user.phoneNumber}<br>
		Возраст: ${sessionScope.user.age}<br>
		Email: ${sessionScope.user.email}<br>
		Status : ${sessionScope.user.role}<br>
		Id: ${sessionScope.user.id}<br>
		
		<a href="Controller?command=go_to_update_user_data_page">Change the data</a><br>
		
		<c:if test="${sessionScope.update_message eq 'successful_update_data'}">
          <h3>Successful update data!</h3>
        </c:if>
            
        <c:if test="${sessionScope.update_message eq 'unsuccessful_update_data'}">
          <h3>Unsuccessful update data!</h3>
        </c:if>
</body>
</html>