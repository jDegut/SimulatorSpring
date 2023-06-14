<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
<%@include file="../rsrc/navbar.jsp"%>
<div class="container-fluid py-3">
	<h2>S'inscrire à une nouvelle mission</h2>
	<c:if test="${missions.size() > 0}">
	<div class="mt-3">
		<table class="table">
			<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Nom de la mission</th>
				<th scope="col">Actions associées - Score minimum</th>
				<th scope="col">S'inscrire</th>
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
					<td><a class="btn btn-outline-success" href="/inscription/add/${mission.id}" role="button">S'inscrire</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
	<c:if test="${missions.size() == 0}">
		<p>Vous êtes déjà inscrit sur toutes les missions.</p>
	</c:if>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
