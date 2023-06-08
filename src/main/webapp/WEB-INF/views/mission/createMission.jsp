<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
	<%@include file="../rsrc/header.jsp" %>
	<body>
		<%@include file="../rsrc/navbar.jsp"%>
		<div class="container-fluid py-3">
			<h2>Créer une nouvelle mission</h2>
			<form method="post" action="/mission/create">
				<div class="mb-3">
					<label for="wording" class="form-label">Nom de la mission</label>
					<input type="text" class="form-control" id="wording" name="wording">
				</div>
				<div class="mb-3">
					<p>Actions à ajouter</p>
					<c:forEach var="action" items='${actions}'>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="${action.id}" name="actionList" id="action_wording_${action.id}">
							<label class="form-check-label" for="action_wording_${action.id}">
								${action.wording}
							</label>
						</div>
					</c:forEach>
				</div>
				<button type="submit" class="btn btn-primary">Créer la mission</button>
			</form>
		</div>
	</body>
	<%@include file="../rsrc/footer.jsp" %>
</html>
