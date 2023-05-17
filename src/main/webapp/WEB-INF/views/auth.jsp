<%@page contentType="text/html;charset=UTF-8"%>
<html>
    <%@include file="header.jsp" %>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container-fluid">
            <div class="col">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Nom d'utilisateur</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Mot de passe</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Se connecter</button>
                </form>
            </div>
            <div class="col">

            </div>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
