<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
<%@include file="../rsrc/navbar.jsp"%>
<div class="container-fluid py-3">
    <h2>Mon compte</h2>
    <div class="row py-3 justify-content-center" style="background-color: snow">
        <div class="col-8">
            <p>Nom de famille : ${user.forename}</p>
            <p>Prénom : ${user.surname}</p>
            <p>Status : ${user.role}</p>
            <hr>
            <p>Nom d'utilisateur : ${user.username}</p>
            <p>Changer de mot de passe : </p>
            <div class="col">
                <form action="/user" method="post" oninput='newPassword2.setCustomValidity(newPassword2.value !== newPassword.value ? "Les mots de passe ne concordent pas" : "")'>
                    <div class="row">
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="col">
                            <input type="password" class="form-control" id="oldPassword" placeholder="Ancien mot de passe" name="oldPassword">
                        </div>
                        <div class="col">
                            <input type="password" class="form-control" id="newPassword" placeholder="Nouveau mot de passe" name="newPassword">
                        </div>
                        <div class="col">
                            <input type="password" class="form-control" id="newPassword2" placeholder="Nouveau mot de passe (confirmer)" name="newPassword2">
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary">Changer</button>
                        </div>
                    </div>
                </form>
                <c:if test="${modif != null}">
                    <c:choose>
                        <c:when test="${isChanged == false}">
                            <div class="alert alert-danger">
                                ${modif}
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-success">
                                    ${modif}
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
