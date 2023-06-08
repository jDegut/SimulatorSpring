<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
	<%@include file="../rsrc/header.jsp" %>
	<body>
		<%@include file="../rsrc/navbar.jsp"%>
		<div class="container-fluid py-3">
			<h2>Modifier un utilisateur</h2>
			<div class="col-8">
				<p>Nom de famille : ${user.forename}</p>
				<p>Prénom : ${user.surname}</p>
				<p>Status : ${user.role}</p>
				<hr>
				<p>Nom d'utilisateur : ${user.username}</p>
				<p>Modifier le rôle :</p>
				<div class="col">
					<form action="/user/role" method="post">
						<div class="row">
							<input type="hidden" name="id" value="${user.id}">
							<div class="col-3">
								<select class="form-select" name="role">
									<option value="admin" ${user.role == "admin" ? 'selected' : ''}>Administrateur</option>
									<option value="learner" ${user.role == "learner" ? 'selected' : ''}>Apprenant</option>
								</select>
							</div>
							<div class="col">
								<button type="submit" class="btn btn-primary">Changer</button>
							</div>
						</div>
					</form>
			</div>
		</div>
	</body>
	<%@include file="../rsrc/footer.jsp" %>
</html>
