<%@page contentType="text/html;charset=UTF-8"%>
<html>
    <%@include file="header.jsp" %>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container-fluid">
            <div class="alert alert-danger" role="alert">
                ${message}
            </div>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
