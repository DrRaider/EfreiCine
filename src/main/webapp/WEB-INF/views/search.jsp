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
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <!-- Custom styles for this template-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/app.css"/>
    <script>var json = ${search_results};</script>
</head>
<body>
<hgroup>
    <h3>Search Movies</h3>
</hgroup>
<c:url var="loginUrl" value="/login" />
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
<div>
    <c:if test="${search_results == null}">
    <table style="border-collapse: collapse;" border="1" class="showResults">
        <tr>
            <td colspan="7">No Results found</td>
        </tr>
    </table>
    </c:if>
    <c:if test="${search_results != null}">
        <div class="container">
            <table id="table">
                <thead>
                <tr>
                    <th data-field="original_title">original_title</th>
                    <th data-field="overview">overview</th>
                    <th data-field="release_date">release_date</th>
                    <th data-field="poster_path">Poster</th>

                </tr>
                </thead>
            </table>
        </div>
    </c:if>
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

    $('#table').bootstrapTable({
        data: json.results
    });
    // Add https://image.tmdb.org/t/p/w500/ + link provided in json to all poster_path
    // And add js script to sort table by date (most recent first)
</script>
</html>







