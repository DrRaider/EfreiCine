<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <link rel="icon" href="../../favicon.ico"/>
        <title>Sign up</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <!-- Custom styles for this template-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/app.css"/>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    </head>
    <body>
        <hgroup>
        <h3>Sign Up</h3>
        </hgroup>
        <%--@elvariable id="userTheater" type="raider.project.EfreiCine.model.ConcatUserTheater"--%>
        <form:form method="POST" modelAttribute="userTheater" class="form-horizontal">
            <h4>User</h4>
            <div class="group">
                <form:input required="true" type="text" path="user.firstName" id="firstName"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>First Name</label>
                <div class="has-error">
                    <form:errors path="user.firstName" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <form:input required="true" type="text" path="user.lastName" id="lastname"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Last Name</label>
                <div class="has-error">
                    <form:errors path="user.lastName" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <form:input required="true" type="text" path="user.ssoId" id="ssoID"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>SSO ID</label>
                <div class="has-error">
                    <form:errors path="user.ssoId" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <form:input required="true" type="text" path="user.password" id="password"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Password</label>
                <div class="has-error">
                    <form:errors path="user.password" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <form:input required="true" type="text" path="user.email" id="email"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Email</label>
                <div class="has-error">
                    <form:errors path="user.email" class="help-inline"/>
                </div>
            </div>
            <h4>Theater</h4>
            <div class="group">
                <form:input required="true" type="text" path="theater.number" id="number"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Number</label>
                <div class="has-error">
                    <form:errors path="theater.number" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <form:input required="true" type="text" path="theater.street" id="street"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Street</label>
                <div class="has-error">
                    <form:errors path="theater.street" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <form:input required="true" type="text" path="theater.city" id="city"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>City</label>
                <div class="has-error">
                    <form:errors path="theater.city" class="help-inline"/>
                </div>
            </div>
            <div class="form-actions">
                <input class="btn btn-primary" type="submit" value="Sign Up"/>
            </div>
        </form:form>
    </body>
    <script>
        $(document).ready(function() {
            $('input').blur(function () {
                var $this = $(this);
                if ($this.val())
                    $this.addClass('used');
                else
                    $this.removeClass('used');
            });
        });
    </script>
</html>







