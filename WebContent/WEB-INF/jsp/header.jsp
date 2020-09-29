<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style><%@include file="/WEB-INF/css/core.css"%></style>
    <link rel="stylesheet" href="reset.css">
</head>
<body>
<!-- START HEADER -->
 <header>
        <div class="header-up">
            <div class="registr-box">
                <ul>
                    <li><a href="Controller?command=go_to_registration_page">Регистрация</a></li>
                    <li><a href="Controller?command=go_to_login_page">Войти</a></li>
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
                        <li><a href="/">Меню</a></li>
                        <li><a href="/">Акции</a></li>
                        <li><a href="/">О нас</a></li>
                        <li><a href="/">Контакты</a></li>
                    </ul>
                </div>           
            </div> 
        </div>
    </header>
<%--END HEADER--%>