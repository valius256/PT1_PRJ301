<%-- 
    Document   : index
    Created on : Jul 11, 2023, 8:16:06 PM
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
        <a href="MainController?action=1">Login</a>
        <form action="MainController" method="post">
            <input type="hidden" value="2" name="action"/>
            <input type="text" name="txtsearch" value="${param.keyword}"/>
            <input type="submit" value="find"/>
        </form>
        <c:set var="list" value="${requestScope.result}"/>
        <c:if test="${list != null}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="c" items="${list}">
                    <form action="MainController" method="post">
                        <input type="hidden" value="7" name="action"/>
                        <input type="hidden" value="${c.id}" name="txtcarid" />

                        <tr>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td>${c.price}</td>
                            <td>${c.status}</td>
                            <td><input type="submit" value="Add to cart"></td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
        </c:if>
        <a href="MainController?action=8">View cart</a>
    </body>
</html>
