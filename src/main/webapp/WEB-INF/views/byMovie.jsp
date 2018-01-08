<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="../../favicon.ico"/>
    <title>Find a movie</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/app.css"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.filtertable.min.js"></script>
    <script>var json = ${movies};</script>
</head>
<body>
    <c:if test="${movies != null}">
        <div class="container">
            <table class="search-table table table-striped">
                <thead>
                <tr>
                    <th data-field="tmdbId" data-formatter="LinkFormatter"></th>
                    <th data-field="originalTitle" data-sortable="true">Original title</th>
                    <th data-field="overview">Overview</th>
                    <th data-field="releaseDate" data-sortable="true">Release date</th>
                    <th data-field="posterPath" data-formatter="ImgFormatter">Poster</th>
                </tr>
                </thead>
            </table>
        </div>
    </c:if>
    <script>
        $(document).ready(function(){
            // language=JQuery-CSS
            $('table.search-table').bootstrapTable({
                data: json
            }).filterTable({minRows: 1}).bootstrapTable('hideLoading');
            $('table.search-table tr').click(function() {
                var href = $(this).find("a").attr("href");
                if(href) {
                    window.location = href;
                }
            });
        });

        /**
         * @return {string}
         */
        function ImgFormatter(value, row, index) {
            return "<img src='"+value+"' height=\"278\" width=\"185\"/>";
        }

        /**
         * @return {string}
         */
        function LinkFormatter(value, row, index) {
            return "<a href='../film/"+value+"'/>";
        }
    </script>
</body>
</html>
