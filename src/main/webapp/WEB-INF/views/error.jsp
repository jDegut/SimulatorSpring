<%@page contentType="text/html;charset=UTF-8"%>
<html>
    <%@include file="rsrc/header.jsp" %>
    <body>
        <%@include file="rsrc/navbar.jsp"%>
        <div class="container-fluid">
            <div class="alert alert-danger mt-3" role="alert">
                ${message}
            </div>
        </div>
    </body>
    <%@include file="rsrc/footer.jsp" %>
</html>
