<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cas de l’ONG</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f4f6f9;
        }

        .header {
            background: #27ae60;
            color: white;
            padding: 30px;
            text-align: center;
        }

        .card-critique {
            border-left: 6px solid #dc3545;
            background: #fff5f5;
        }

        .card-elevee {
            border-left: 6px solid #ffc107;
            background: #fffbea;
        }

        .card-normal {
            border-left: 6px solid #28a745;
        }

        .objectif {
            font-size: 14px;
            color: #555;
        }
    </style>
</head>

<body>

<div class="header">
    <h1>Cas humanitaires disponibles</h1>
    <p>Votre don peut sauver des vies ❤️</p>
</div>

<div class="container my-5">

<!-- SI AUCUN CAS -->
<c:if test="${empty cases}">
    <div class="alert alert-secondary text-center">
        Aucun cas disponible pour cette ONG.
    </div>
</c:if>

<div class="row">

<c:forEach var="c" items="${cases}">

<div class="col-md-6 mb-4">

<div class="card p-3
    <c:choose>
        <c:when test="${c.niveauUrgence == 'CRITIQUE'}">card-critique</c:when>
        <c:when test="${c.niveauUrgence == 'ELEVEE'}">card-elevee</c:when>
        <c:otherwise>card-normal</c:otherwise>
    </c:choose>
">

<h4>${c.titre}</h4>

<!-- BADGE URGENCE -->
<span class="badge
    <c:choose>
        <c:when test="${c.niveauUrgence == 'CRITIQUE'}">bg-danger</c:when>
        <c:when test="${c.niveauUrgence == 'ELEVEE'}">bg-warning</c:when>
        <c:otherwise>bg-success</c:otherwise>
    </c:choose>
">
    ${c.niveauUrgence}
</span>

<p class="mt-2">
    <strong>Localisation :</strong> ${c.localisation}
</p>

<p>${c.description}</p>

<!-- INFO OBJECTIF -->
<div class="objectif">
    🎯 Objectif : ${c.objectif} MRU<br/>
    💰 Reçu : ${c.totalDons} MRU
</div>

<hr/>

<!-- BOUTON DON OU BLOQUÉ -->
<c:choose>

    <c:when test="${c.bloque}">
        <button class="btn btn-secondary w-100" disabled>
            🎯 Objectif atteint
        </button>
    </c:when>

    <c:otherwise>
        <a class="btn btn-success w-100"
           href="${pageContext.request.contextPath}/donate?caseId=${c.id}">
            Faire un don ❤️
        </a>
    </c:otherwise>

</c:choose>

</div>
</div>

</c:forEach>

</div>
</div>

</body>
</html>
