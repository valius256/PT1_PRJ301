<%-- 
    Document   : adminpage
    Created on : Jul 11, 2023, 11:08:45 PM
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
        <c:set var="us" value="${sessionScope.loginedUser}" />
        <c:if test="${us != null}">
            <h2>Welcome ${us.fullname} come back</h2>
            <p><a href="MainController?action=6">logout</a></p>
        </c:if>
        <c:if test="${us == null}">
            <c:redirect url="MainController" />
        </c:if>
    </body>
</html>
