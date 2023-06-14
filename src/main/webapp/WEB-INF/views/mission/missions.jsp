<%@page contentType="text/html;charset=UTF-8"%>
            <html>
    <%@include file="../rsrc/header.jsp" %>
    <body>
    <%@include file="../rsrc/navbar.jsp"%>
        <div class="container-fluid py-3">
            <h2>Liste des missions</h2>
            <div class="mt-3">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nom de la mission</th>
                            <th scope="col">Actions associées - Score minimum</th>
                            <th scope="col">Modifier</th>
                            <th scope="col">Supprimer</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mission" items="${missions}">
                            <tr>
                                <th scope="row">${mission.id}</th>
                                <td>${mission.wording}</td>
                                <td>
                                    <c:forEach var="action" items="${mission.actions}">
                                        ${action.wording} - ${action.scoreMinimum} <br><br>
                                    </c:forEach>
                                </td>
                                <td><a class="btn btn-outline-primary" href="/mission/${mission.id}" role="button">Modifier</a></td>
                                <td><a class="btn btn-danger" href="/mission/delete/${mission.id}" role="button">Supprimer</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <%@include file="../rsrc/footer.jsp" %>
</html>
