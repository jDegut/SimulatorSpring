<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary py-3">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <img src="${pageContext.request.contextPath}/resources/img/logo-polytech.png" alt="Logo" height="24" class="d-inline-block align-text-top">
            Simulator Spring
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Accueil</a>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/mission">Missions</a>
                    </li>
                    <sec:authorize access="hasAuthority('admin')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Apprenants
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/learner">Gérer un apprenant</a></li>
                                <li><a class="dropdown-item" href="/learner/add">Enregistrer un apprenant</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Jeux
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/game">Lister les jeux</a></li>
                                <li><a class="dropdown-item" href="/game/add">Créer un jeu</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Actions
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/action">Gérer les actions</a></li>
                                <li><a class="dropdown-item" href="/action/add">Créer une action</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link" href="/auth">S'authentifier</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>