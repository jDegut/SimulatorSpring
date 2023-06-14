<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
<%@include file="../rsrc/navbar.jsp"%>
<div class="container-fluid py-3">
    <h2>Liste des actions</h2>
    <div class="mt-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nom de l'action</th>
                <th scope="col">Score minimum</th>
                <th scope="col">Action associée (si parent)</th>
                <th scope="col">Modifier</th>
                <th scope="col">Supprimer</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="action" items="${actions}">
                <tr>
                    <th scope="row">${action.id}</th>
                    <td>${action.wording}</td>
                    <td>${action.scoreMinimum}</td>
                    <td>${action.previousActionWording}</td>
                    <td><a class="btn btn-outline-primary" href="/action/${action.id}" role="button">Modifier</a></td>
                    <td><a class="btn btn-danger" href="/action/delete/${action.id}" role="button">Supprimer</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
