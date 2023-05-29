<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
<%@include file="../rsrc/navbar.jsp"%>
    <div class="container-fluid py-3">
        <h2>Mission : ${mission.wording}</h2>
        <div class="mt-3">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Action</th>
                    <th scope="col">Score minimum</th>
                    <th scope="col">Action à effectuer au préalable</th>
                    <th scope="col">Enlever l'action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="action" items="${mission.actions}">
                    <tr>
                        <th scope="row">${action.id}</th>
                        <td>${action.wording}</td>
                        <td>${action.scoreMinimum}</td>
                        <td>${action.previousActionWording}</td>
                        <td><a class="btn btn-danger" href="/mission/${mission.id}/remove/${action.id}" role="button">Enlever</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${otherActions.size() > 0}">
        <div class="mt-5">
            <h4>Ajouter une action</h4>
            <form action="/mission/${mission.id}/action/add" method="post" class="ms-3">
                <c:forEach var="newAction" items="${otherActions}">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="id" id="newActionCheck" value="${newAction.id}">
                        <label class="form-check-label" for="newActionCheck">
                            ${newAction.wording} - ${newAction.scoreMinimum}
                        </label>
                    </div>
                </c:forEach>
                <button type="submit" class="btn btn-primary mt-3">Ajouter</button>
            </form>
        </div>
        </c:if>
    </div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
