<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
<%@include file="../rsrc/navbar.jsp"%>
    <div class="container-fluid py-3">
        <h2>Liste des apprenants</h2>
        <div class="mt-3">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom de famille</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Adresse email</th>
                    <th scope="col">Pseudo</th>
                    <th scope="col">Gérer</th>
                    <th scope="col">Supprimer</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="learner" items="${learners}">
                    <tr>
                        <th scope="row">${learner.id}</th>
                        <td>${learner.forename}</td>
                        <td>${learner.surname}</td>
                        <td>${learner.email}</td>
                        <td>${learner.username}</td>
                        <td><a class="btn btn-outline-primary" href="/user/modify/${learner.id}" role="button">Gérer</a></td>
                        <td><a class="btn btn-danger" href="/user/delete/${learner.id}" role="button">Supprimer</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
