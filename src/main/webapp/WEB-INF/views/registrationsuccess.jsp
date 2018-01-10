<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>
<body>
    <div class="success">
        <c:if test="${fail != null}">
            Fail message : ${fail}
            <br/>
            Go to <a href="<c:url value='/register' />">Register</a> or <a href="<c:url value="/" />">Home</a>
        </c:if>
        <c:if test="${success != null}">
            Confirmation message : ${success}
            <br/>
            Go to <a href="<c:url value='/search' />">Search</a> or <a href="<c:url value="/logout" />">Logout</a>
        </c:if>
    </div>
     
</body>
</html> 