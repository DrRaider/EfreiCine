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
<header>
    <h3>Search Movies</h3>
</header>
<c:url var="searchUrl" value="/search" />
<form action="${searchUrl}" method="post">
    <div class="group">
        <input type="text" id="movie" name="movie"/>
        <span class="highlight"></span>
        <span class="bar"></span>
        <label>Movie Title</label>
    </div>
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    <div class="form-actions">
        <input class="btn btn-primary" type="submit" value="Search"/>
    </div>
</form>
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







