<%-- 
    Document   : userpage
    Created on : Jul 11, 2023, 11:08:52 PM
    Author     : Quang Phat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                text-decoration: aquamarine;
                text-align: center;
                background-color: azure;
            }
        </style>
    </head>

    <body>
        <c:set var="loginedUser" value="${sessionScope.loginedUser}" />

        <c:choose>
            <c:when test="${not empty loginedUser}">
                <h2>Welcome ${loginedUser.fullname} come back</h2>
                <p><a href="MainController?action=6">logout</a></p>
            </c:when>
            <c:otherwise>
                <c:redirect url="MainController" />
            </c:otherwise>
        </c:choose>

        <a href="MainController?action=8">View cart</a><br>
        <a href="MainController">Home</a><br>
        <a href="MainController">Profile</a><br>
        <a href="MainController?action=11">View order history</a>
    </body>
</html>
