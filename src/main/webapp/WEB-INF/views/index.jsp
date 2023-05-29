<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
    <%@include file="rsrc/header.jsp" %>
    <body>
        <%@include file="rsrc/navbar.jsp"%>
        <div class="container-fluid py-3">
            <h2>Accueil</h2>
            <div class="row py-3" style="background-color: snow">
                <h4>Bienvenue dans l'application Permis Piste !</h4>
                <div class="col-9 mx-auto pt-3">
                    <div class="col">
                        <h6>Nous sommes le <%= new SimpleDateFormat("d MMMMM yyyy HH:mm:ss z").format(new Date())%></h6>
                        <br>
                        <p>Cette application vous permettra de suivre pas à pas votre apprentissage.<br>
                        En vous souhaitant une agréable visite !</p>
                        <br>
                        <h4>Pour commencez, vous pouvez vous :</h4>
                        <ol>
                            <li><a>Enregistrer en tant qu'apprenant</a></li>
                            <li><a>Inscrire à un jeu depuis la liste</a></li>
                            <li><a>Accéder à la liste des missions pour valider des missions</a></li>
                        </ol>
                        <h5>D'autres informations comme la liste des apprenants ou des
                        <br>actions sont disponibles en haut à droite.</h5>
                    </div>
                    <div class="col-2">

                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file="rsrc/footer.jsp" %>
</html>
