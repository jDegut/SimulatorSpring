<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
<%@include file="../rsrc/navbar.jsp"%>
<div class="container-fluid py-3">
	<h2>Mes inscriptions</h2>
	<div class="mt-3">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nom de la mission</th>
					<th scope="col">Actions associées</th>
				</tr>
			</thead>
			<tbody>
			<!--
				On prend les missions grâce au KeySet de la map (Set non ordonné), puis on le trie en fonction de l'id
			-->
			<c:forEach var="mission" items="${missionsMap.keySet().stream()
			.sorted((m1,m2) -> m1.getId().compareTo(m2.getId()))
			.toList()}">
				<tr>
					<th scope="row">${mission.id}</th>
					<td>${mission.wording}</td>
					<td colspan="4">
						<table class="table mb-0">
							<thead>
								<tr>
									<th scope="col">Nom de l'action</th>
									<th scope="col">Mon score actuel</th>
									<th scope="col">Score minimal pour valider</th>
									<th scope="col">Valider des indicateurs</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="inscriptionAction" items="${missionsMap.get(mission)}">
								<tr>
									<td>${inscriptionAction.fkActionWording}</td>
									<td>${inscriptionAction.score == null ? "NaN" : inscriptionAction.score}</td>
									<td>${inscriptionAction.fkActionScoreMinimum}</td>
									<c:if test="${inscriptionAction.fkActionScoreMinimum > inscriptionAction.score || inscriptionAction.score == null}">
										<td><a class="btn btn-outline-primary" href="/inscription/${inscriptionAction.fkInscriptionId}/action/${inscriptionAction.fkActionId}" role="button">Accéder</a></td>
									</c:if>
									<c:if test="${inscriptionAction.fkActionScoreMinimum <= inscriptionAction.score}">
										<td><a class="btn btn-outline-success" href="/inscription/${inscriptionAction.fkInscriptionId}/action/${inscriptionAction.fkActionId}" role="button">Done</a></td>
									</c:if>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
