<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../../favicon.ico"/>
    <title>Create screening</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/app.css"/>
    <script>var json = ${sessions};</script>
</head>
<body>
<hgroup>
    <h2>Screenings for ${movie.originalTitle}</h2>
</hgroup>
<hr>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="media">
                <a href="#" class="pull-left">
                    <img src='${movie.posterPath}' class="media-object" height="450" width="300"/>
                </a>
                <div class="row">
                    <div class="media-body" style="padding-left: 10px;">
                        <h2 class="media-heading">
                            ${movie.originalTitle} - ${movie.releaseDate.getYear() + 1900}
                        </h2>
                        <h3>Overview</h3>
                        <h4>${movie.overview}</h4>
                        <h3>Cast</h3>
                        <h4>${movie.cast}.</h4>
                        <h3>Producers</h3>
                        <h4>${movie.producer}.</h4>
                        <h3>Directors</h3>
                        <h4>${movie.director}.</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <br>
        <br>
        <h3 style="display: inline">Theater :</h3>
        <h4 style="display: inline">${theater.number}, ${theater.street} - ${theater.city}.</h4>
        <br>
        <br>
        <h3 style="display: inline">Start date : </h3>
        <h4 style="display: inline">${screening.startDate}.</h4>
        <br>
        <h3 style="display: inline">End date : </h3>
        <h4 style="display: inline">${screening.endDate}.</h4>
        <br>
        <h3 style="display: inline">Age limit : </h3>
        <h4 style="display: inline">Forbidden under ${screening.ageLimit} yo.</h4>
        <br><br>
    </div>
    <div class="col-md-3">
    <c:if test="${sessions != null}">

            <table class="search-table table table-striped">
                <thead>
                <tr>
                    <th data-field="day">Day</th>
                    <th data-field="hour">Start time</th>
                </tr>
                </thead>
            </table>

    </c:if>
    </div>
    <a href="/">Home</a>
</div>

<script>
    $(document).ready(function() {
            // language=JQuery-CSS
            $('table.search-table').bootstrapTable({
                data: json,

            }).bootstrapTable('hideLoading');
        });
</script>
</body>
</html>
