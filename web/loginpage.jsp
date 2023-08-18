<%-- 
    Document   : loginpages
    Created on : Jul 11, 2023, 8:40:33 PM
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
       <form action="MainController" method="post">
            <input type="hidden" value="3" name="action"/>
            <input type="text" name="txtuserid" placeholder="enter userid" required/>
            <br>
            <input type="password" name="txtpassword" placeholder="enter password" required/>
            <br> 
            <input type="submit" value="login">
        </form>
    <c:if test="${not empty ERROR}">
        <font style="color: red; font-size: 14">${ERROR}</font>
    </c:if>
    </body>
</html>
