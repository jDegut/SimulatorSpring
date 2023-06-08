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
				<th scope="col">Action requise</th>
				<th scope="col">Score minimum</th>
				<sec:authorize access="hasAuthority('admin')">
					<th scope="col">Modifier</th>
					<th scope="col">Supprimer</th>
				</sec:authorize>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="action" items="${actions}">
				<tr>
					<th scope="row">${action.id}</th>
					<td>${action.wording}</td>
					<td>${action.previousActionWording}</td>
					<td>${action.scoreMinimum}</td>
					<sec:authorize access="hasAuthority('admin')">
						<td><a class="btn btn-outline-primary" href="/action/modify/${action.id}" role="button">Modifier</a></td>
						<td><a class="btn btn-danger" href="/action/delete/${action.id}" role="button">Supprimer</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<h2>Liste des indicateurs</h2>
	<div class="mt-3">
		<table class="table">
			<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Nom de l'indicateur</th>
				<th scope="col">Inscris dans l'action</th>
				<th scope="col">Valeur si rempli</th>
				<th scope="col">Valeur si non rempli</th>
				<sec:authorize access="hasAuthority('admin')">
					<th scope="col">Modifier</th>
					<th scope="col">Supprimer</th>
				</sec:authorize>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="indicator" items="${indicators}">
				<tr>
					<th scope="row">${indicator.id}</th>
					<td>${indicator.wording}</td>
					<td>${indicator.fkActionWording}</td>
					<td>${indicator.valueIfCheck}</td>
					<td>${indicator.valueIfUnCheck}</td>
					<sec:authorize access="hasAuthority('admin')">
						<td><a class="btn btn-outline-primary" href="/action/modify/${action.id}" role="button">Modifier</a></td>
						<td><a class="btn btn-danger" href="/action/delete/${action.id}" role="button">Supprimer</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="clear"></div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
