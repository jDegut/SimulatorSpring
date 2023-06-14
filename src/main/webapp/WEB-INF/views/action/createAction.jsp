<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
	<%@include file="../rsrc/header.jsp" %>
	<body>
		<%@include file="../rsrc/navbar.jsp"%>
		<div class="container-fluid py-3">
			<div class="col-5">
				<h2>Créer une nouvelle action</h2>
				<form method="post" action="/action/create">
					<div class="mb-3">
						<label for="wording" class="form-label">Nom de l'action</label>
						<input type="text" class="form-control" id="wording" name="wording">
					</div>
					<div class="mb-3">
						<label for="scoreMin" class="form-label">Score minimum</label>
						<input type="number" min="0" max="1000" class="form-control" id="scoreMin" name="scoreMinimum">
					</div>
					<div class="mb-3">
						<label for="fkAction" class="form-label">Action dépendante (si existante)</label>
						<select id="fkAction" class="form-select" aria-label="sel" name="previousActionId">
							<option selected value="${null}">Aucune</option>
							<c:forEach items="${actions}" var="action">
								<option value="${action.id}">${action.wording}</option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">Créer la mission</button>
				</form>
			</div>
		</div>
	</body>
	<%@include file="../rsrc/footer.jsp" %>
</html>
