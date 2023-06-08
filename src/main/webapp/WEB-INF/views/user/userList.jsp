<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
<%@include file="../rsrc/navbar.jsp"%>
    <div class="container-fluid py-3">
        <h2>Liste des utilisateurs</h2>
        <div class="mt-3">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom de famille</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Adresse email</th>
                    <th scope="col">Pseudo</th>
                    <th scope="col">Rôle</th>
                    <th scope="col">Gérer</th>
                    <th scope="col">Supprimer</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.forename}</td>
                        <td>${user.surname}</td>
                        <td>${user.email}</td>
                        <td>${user.username}</td>
                        <td>${user.role}</td>
                        <td><a class="btn btn-outline-primary" href="/user/modify/${user.id}" role="button">Gérer</a></td>
                        <td><a class="btn btn-danger" href="/user/delete/${user.id}" role="button">Supprimer</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
