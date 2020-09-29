<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Update user data</title>
</head>
<body>

		<form action="Controller" method="post" >
		
		<input type="hidden" name="command" value="update_user_data" />
		<input type="hidden" name="id" value="${sessionScope.user.id}" />
		
		Update name:
        <input type="text" name="name" pattern="[a-zA-Zа-яА-ЯЁё]{3,15}" value="${sessionScope.user.name}"/><br>
        Update surname:
        <input type="text" name="surname" pattern="[a-zA-Zа-яА-ЯЁё]{3,20}" value="${sessionScope.user.surname}"/><br>
        Update patronymic:
        <input type="text" name="patronymic" pattern="[a-zA-Zа-яА-ЯЁё]{3,20}" value="${sessionScope.user.patronymic}"/><br>
        Update phone number:
		<input type="tel" name="phoneNumber" value="${sessionScope.user.phoneNumber}"/><br>
		Update age:
        <input type="number" min="6" max="100" name="age" pattern="\d+" value="${sessionScope.user.age}" /><br>
        Update email:
        <input type="email" name="email" value="${sessionScope.user.email}" /><br>
        
        <input type="submit" value="Update" />
        </form>
	
</body>
</html>