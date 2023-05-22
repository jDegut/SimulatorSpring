<%@page contentType="text/html;charset=UTF-8"%>
<html>
    <%@include file="header.jsp" %>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container-fluid py-5">
            <div class="row justify-content-center">
                <div class="col-5">
                    <h4>Se connecter</h4>
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
                <div class="col-5 border-start">
                    <h4>S'inscrire</h4>
                    <form action="${pageContext.request.contextPath}/register" method="post">
                        <div class="mb-3">
                            <label for="lastname_register" class="form-label">Nom de famille</label>
                            <input type="text" class="form-control" id="lastname_register" name="forename">
                        </div>
                        <div class="mb-3">
                            <label for="firstname_register" class="form-label">Prénom</label>
                            <input type="text" class="form-control" id="firstname_register" name="surname">
                        </div>
                        <div class="mb-3">
                            <label for="email_register" class="form-label">Email</label>
                            <input type="text" class="form-control" id="email_register" name="email">
                        </div>
                        <hr>
                        <div class="mb-3">
                            <label for="username_register" class="form-label">Nom d'utilisateur</label>
                            <input type="text" class="form-control" id="username_register" name="username">
                        </div>
                        <div class="mb-3">
                            <label for="password_register" class="form-label">Mot de passe</label>
                            <input type="password" class="form-control" id="password_register" name="password">
                        </div>
                        <button type="submit" class="btn btn-primary">S'inscrire</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
