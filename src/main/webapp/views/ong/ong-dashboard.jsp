<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Dashboard ONG</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body { background: #f4f6f9; }

        .header {
            background: linear-gradient(135deg, #27ae60, #1e8449);
            color: white;
            padding: 40px 20px;
            text-align: center;
        }

        .card-info {
            border-left: 5px solid #27ae60;
        }
    </style>
</head>

<body>

<div class="header">
    <h1>Dashboard ONG</h1>
    <p>Gérez vos cas et consultez les dons reçus</p>
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
        <div class="card card-info p-3">
            <h5>Nom</h5>
            <p>${ong.nom}</p>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card card-info p-3">
            <h5>Domaine</h5>
            <p>${ong.domaine}</p>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card card-info p-3">
            <h5>Statut</h5>
            <span class="badge bg-info">
                ${ong.statutValidation}
            </span>
        </div>
    </div>
</div>

<!-- DESCRIPTION -->
<div class="card mb-4">
    <div class="card-body">
        <h5>Description</h5>
        <p>${ong.description}</p>
    </div>
</div>

<!-- BOUTON CREER CAS -->
<div class="text-end mb-3">
    <a class="btn btn-success"
       href="${pageContext.request.contextPath}/views/ong/create-case.jsp">
        ➕ Créer un cas
    </a>
</div>

<!-- LISTE DES CAS + DONS -->
<div class="card">
<div class="card-body">

<h4>Mes cas et dons reçus</h4>

<c:if test="${empty cases}">
    <div class="alert alert-secondary">
        Aucun cas créé.
    </div>
</c:if>

<c:if test="${not empty cases}">

<table class="table table-bordered">
<thead class="table-success">
<tr>
    <th>Titre</th>
    <th>Description</th>
    <th>Dons reçus</th>
</tr>
</thead>

<tbody>

<c:forEach var="c" items="${cases}">
<tr>
    <td>${c.titre}</td>
    <td>${c.description}</td>

    <td>
        <c:choose>

            <c:when test="${empty donsParCas[c.id]}">
                <span class="text-muted">
                    Aucun don pour ce cas
                </span>
            </c:when>

            <c:otherwise>
                <c:forEach var="d" items="${donsParCas[c.id]}">
                    <div>
                        💰 ${d.montant} MRU
                        <small class="text-muted">
                            (${d.dateDonation})
                        </small>
                    </div>
                </c:forEach>
            </c:otherwise>

        </c:choose>
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
