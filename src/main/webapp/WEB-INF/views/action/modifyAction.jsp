<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
    <%@include file="../rsrc/navbar.jsp"%>
    <div class="container-fluid py-3">
        <h2>Action : ${action.wording}</h2>
        <div class="mt-3">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Indicateur</th>
                    <th scope="col">Valeur si effectué</th>
                    <th scope="col">Valeur sinon</th>
                    <th scope="col">Enlever l'indicateur</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="indicator" items="${indicators}">
                    <tr>
                        <th scope="row">${indicator.id}</th>
                        <td>${indicator.wording}</td>
                        <td>${indicator.valueIfCheck}</td>
                        <td>${indicator.valueIfUnCheck}</td>
                        <td><a class="btn btn-danger" href="/action/${action.id}/remove/${indicator.id}" role="button">Enlever</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col">
            <h2>Ajouter un nouvel indicateur</h2>
            <form method="post" action="/indicator/create">
                <input type="hidden" value="${action.id}" name="fkActionId">
                <div class="mb-3">
                    <label for="wording2" class="form-label">Nom de l'indicateur</label>
                    <input type="text" class="form-control" id="wording2" name="wording">
                </div>
                <div class="mb-3">
                    <label for="valueIfCheck" class="form-label">Valeur si effectué</label>
                    <input type="number" min="-1000" max="1000" class="form-control" id="valueIfCheck" name="valueIfCheck">
                </div>
                <div class="mb-3">
                    <label for="valueIfUnCheck" class="form-label">Valeur sinon</label>
                    <input type="number" min="-1000" max="1000" class="form-control" id="valueIfUnCheck" name="valueIfUnCheck">
                </div>
                <button type="submit" class="btn btn-primary">Créer l'indicateur</button>
            </form>
        </div>
    </div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
