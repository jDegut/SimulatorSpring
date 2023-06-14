<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<%@include file="../rsrc/header.jsp" %>
<body>
	<%@include file="../rsrc/navbar.jsp"%>
	<div class="container-fluid py-3">
		<h2>Action ${actionInscription.fkActionWording}</h2>
		<p class="italic">Commencée le ${actionInscription.fkInscriptionDate}</p>
		<div class="mt-3">
			<table class="table">
				<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nom de l'indicateur</th>
					<th scope="col">Score</th>
					<th scope="col">Valider/Invalider</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="indicator" items="${indicatorInscriptions.keySet().stream()
				.sorted((i1,i2) -> i1.getId().compareTo(i2.getId()))
				.toList()}">
					<tr>
						<th scope="row">${indicator.id}</th>
						<td>${indicator.wording}</td>
						<td>${indicatorInscriptions.get(indicator).done ? indicator.valueIfCheck : indicator.valueIfUnCheck}</td>
						<c:if test="${indicatorInscriptions.get(indicator).done == false}">
							<td><a class="btn btn-outline-primary" href="/inscription/${indicatorInscriptions.get(indicator).fkInscription}/action/${indicatorInscriptions.get(indicator).fkAction}/indicator/${indicatorInscriptions.get(indicator).fkIndicator}" role="button">Valider</a></td>
						</c:if>
						<c:if test="${indicatorInscriptions.get(indicator).done == true}">
							<td><a class="btn btn-outline-danger" href="/inscription/${indicatorInscriptions.get(indicator).fkInscription}/action/${indicatorInscriptions.get(indicator).fkAction}/indicator/${indicatorInscriptions.get(indicator).fkIndicator}" role="button">Invalider</a></td>
						</c:if>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<%@include file="../rsrc/footer.jsp" %>
</html>
