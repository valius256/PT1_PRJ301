<%-- 
    Document   : viewcart
    Created on : Jul 11, 2023, 11:10:05 PM
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
        <c:set var="cart" value="${sessionScope.cart}"/>
        <c:if test="${cart!=null}">
            <h1>Your cart</h1>
            <table>
                <tr>
                    <th>ID </th>
                    <th>Name </th>
                    <th>Price </th>
                    <th>Quantity </th>
                    <th>Total </th>
                    <th>Action </th>
                </tr>

                <c:set var="sum" value="0"/>
                <c:set var="totalCars" value="0" />
                <c:forEach var="c" items="${cart.keySet()}">
                    <c:set var="quantity" value="${cart.get(c)}"/>
                    <c:set var="total" value="${quantity * c.price}"/>
                    <c:set var="sum" value="${sum + total}"/>
                    <c:set var="totalCars" value="${totalCars + quantity}" />


                    <form action="MainController" method="post">
                        <input type="hidden" value="9" name="action"/>
                        <!--                <input type="hidden" value="10" name="remove"/>-->
                        <input type="hidden" value="${c.id}" name="txtcarid"/>
                        <tr>
                            <td>${c.id} </td>
                            <td>${c.name} </td>
                            <td>${c.price} </td>
                            <td><input type="number" min="1" value="${quantity}" name="txtquantity"></td>
                            <td>${total}USD</td>
                            <td><input type="submit" value="remove" onclick="return warning()" name="btnremove">
                                <input type="submit" value="update">       
                            </td>
                        </tr>
                    </form>

                </c:forEach>
            </table>
           
            <h3>Total cars: ${totalCars}</h3>
            <h3>Total money paid: ${sum} USD</h3>

            <p><a href="MainController?action=">Back</a></p>    
            
            
        </c:if>
    </body>
</html>
