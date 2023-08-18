<%-- 
    Document   : test
    Created on : Jul 27, 2023, 12:59:11 PM
    Author     : Quang Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
    <html>
        <%! int x; %>
        <%
            public String getDescrition() {
                return "JAD Test";
            };
        %>
        <%= getDescrition()%>
    </body>
</html>
