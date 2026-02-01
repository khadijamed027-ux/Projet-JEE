<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Dashboard ONG</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f4f6f9;
        }

        .header {
            background: linear-gradient(135deg, #27ae60, #1e8449);
            color: white;
            padding: 40px 20px;
            text-align: center;
        }

        .card-info {
            border-left: 5px solid #27ae60;
        }

        .badge-validation {
            font-size: 14px;
            padding: 8px 12px;
        }
    </style>
</head>

<body>

<!-- HEADER -->
<div class="header">
    <h1>Dashboard ONG</h1>
    <p>Gérez vos cas et suivez vos actions solidaires</p>
</div>

<div class="container my-5">

    <!-- MESSAGE ERREUR -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">
            ${error}
        </div>
    </c:if>

    <!-- INFOS ONG -->
    <div class="row mb-4">
        <div class="col-md-4">
            <div class="card card-info shadow-sm p-3">
                <h5 class="text-success">Nom</h5>
                <p class="fw-bold">${ong.nom}</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card card-info shadow-sm p-3">
                <h5 class="text-success">Domaine</h5>
                <p>${ong.domaine}</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card card-info shadow-sm p-3">
                <h5 class="text-success">Statut</h5>

                <span class="badge bg-info badge-validation">
                    ${ong.statutValidation}
                </span>
            </div>
        </div>
    </div>

    <!-- DESCRIPTION -->
    <div class="card shadow-sm mb-4">
        <div class="card-body">
            <h5 class="text-success">Description</h5>
            <p>${ong.description}</p>
        </div>
    </div>

    <!-- BOUTON CREATION CAS -->
    <div class="text-end mb-4">
        <a class="btn btn-success btn-lg"
           href="${pageContext.request.contextPath}/views/ong/create-case.jsp">
            ➕ Créer un nouveau cas
        </a>
    </div>

    <!-- LISTE DES CAS -->
    <div class="card shadow-sm">
        <div class="card-body">
            <h4 class="mb-3">Mes cas publiés</h4>

            <c:if test="${empty cases}">
                <div class="alert alert-secondary text-center">
                    Aucun cas créé pour le moment.
                </div>
            </c:if>

            <c:if test="${not empty cases}">
            <table class="table table-hover">
                <thead class="table-success">
                <tr>
                    <th>Titre</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Statut</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="c" items="${cases}">
                    <tr>
                        <td class="fw-bold">${c.titre}</td>
                        <td>${c.description}</td>
                        <td>
                            <span class="badge bg-primary">
                                ${c.typeCase}
                            </span>
                        </td>
                        <td>
                            <span class="badge bg-warning text-dark">
                                ${c.statut}
                            </span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:if>

        </div>
    </div>

</div>

</body>
</html>
