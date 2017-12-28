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
        <title>Create screening</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonloginymous">
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/app.css"/>
    </head>
    <body>
        <hgroup>
            <h1>Create screening for ${movie.originalTitle}</h1>
        </hgroup>
        <hr>
        <hr>
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
        </div>
        <%--@elvariable id="screeningSession" type="raider.project.EfreiCine.model.ScreeningSession"--%>
        <form:form method="POST" modelAttribute="screeningSession" class="form-horizontal">
            <h4>Screening</h4>
            <div class="group">
                <form:input required="true" type="hidden" path="screening.theaterId" value="${theater.id}" id="theaterId"/>
                <div class="has-error">
                    <form:errors path="screening.theaterId" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <h5>Start date</h5>
                <form:input required="true" type="date" path="screening.startDate" id="startDate"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <div class="has-error">
                    <form:errors path="screening.startDate" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <h5>End date</h5>
                <form:input required="true" type="date" path="screening.endDate" id="endDate"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <div class="has-error">
                    <form:errors path="screening.endDate" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <h5>Age limit</h5>
                <form:input required="true" type="number" path="screening.ageLimit" id="ageLimit"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <div class="has-error">
                    <form:errors path="screening.ageLimit" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <h5>Day</h5>
                <form:select path="session.day">
                    <option value="Monday">Monday</option>
                    <option value="Tuesday">Tuesday</option>
                    <option value="Wednesday">Wednesday</option>
                    <option value="Thursday">Thursday</option>
                    <option value="Friday">Friday</option>
                    <option value="Saturday">Saturday</option>
                    <option value="Sunday">Sunday</option>
                </form:select>
                <span class="highlight"></span>
                <span class="bar"></span>
                <div class="has-error">
                    <form:errors path="session.day" class="help-inline"/>
                </div>
            </div>
            <div class="group">
                <h5>Schedule</h5>
                <input required="true" type="time" name="hour" id="hour"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <div class="has-error">
                    <form:errors path="session.hour" class="help-inline"/>
                </div>
            </div>
            <div class="form-actions">
                <input class="btn btn-primary" type="submit" value="Add screening"/>
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
            $('input[type=checkbox]').on('change', function () {
                if ($('input[type=checkbox]:checked').length > 3) {
                    $(this).prop('checked', false);
                    alert("allowed only 3");
                }
            });
        });
    </script>
</html>
