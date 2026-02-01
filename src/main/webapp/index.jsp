<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>ONG Connect | Plateforme de dons</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f5f7fa;
        }
        .hero {
            background: linear-gradient(120deg, #0d6efd, #0b5ed7);
            color: white;
            padding: 80px 20px;
        }
        .ong-card {
            transition: transform 0.2s;
        }
        .ong-card:hover {
            transform: translateY(-5px);
        }
        footer {
            background: #0d6efd;
            color: white;
        }
    </style>
</head>

<body>

<!-- 🔷 NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#">ONG Connect</a>

        <div class="ms-auto">
            <a href="${pageContext.request.contextPath}/views/auth/login.jsp"
               class="btn btn-outline-light btn-sm">
                Connexion ONG / Admin
            </a>
        </div>
    </div>
</nav>

<!-- 🔷 HERO -->
<section class="hero text-center">
    <div class="container">
        <h1 class="fw-bold">Soutenez des causes qui comptent</h1>
        <p class="lead mt-3">
            Découvrez les ONG partenaires et contribuez aux cas humanitaires
        </p>
    </div>
</section>

<!-- 🔷 ONG LIST -->
<div class="container my-5">
    <h2 class="text-center mb-4">ONG partenaires</h2>

    <div class="row">
        <c:forEach var="ong" items="${ongs}">
            <div class="col-md-4 mb-4">
                <div class="card ong-card shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title fw-bold">${ong.nom}</h5>

                        <span class="badge bg-success mb-2">
                            ${ong.domaine}
                        </span>

                        <p class="card-text mt-2">
                            ${ong.description}
                        </p>
                    </div>

                    <div class="card-footer bg-white border-0">
                        <a href="${pageContext.request.contextPath}/ong/public?id=${ong.id}"
                           class="btn btn-primary w-100">
                            Voir les cas
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty ongs}">
            <div class="col-12 text-center text-muted">
                Aucune ONG disponible pour le moment.
            </div>
        </c:if>
    </div>
</div>

<!-- 🔷 FOOTER -->
<footer class="text-center py-3">
    <p class="mb-0">
        © 2026 ONG Connect — Plateforme solidaire
    </p>
</footer>

</body>
</html>
