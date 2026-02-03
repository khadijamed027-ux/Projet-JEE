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

<!-- BOUTON NOTIFICATIONS -->
<a class="btn btn-warning m-3"
   href="${pageContext.request.contextPath}/notifications">

   📩 Notifications ONG

   <c:if test="${badgeOng > 0}">
      <span class="badge bg-danger">${badgeOng}</span>
   </c:if>

</a>

<div class="container my-5">

<h4>🚨 Cas critiques</h4>

<c:forEach var="c" items="${cases}">
<c:if test="${c.niveauUrgence == 'ELEVEE' ||
             c.niveauUrgence == 'CRITIQUE'}">

<div class="alert alert-danger shadow">
   <b>${c.titre}</b><br/>
   Urgence : ${c.niveauUrgence}
</div>

</c:if>
</c:forEach>


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

<!-- BOUTON CREER CAS -->
<div class="text-end mb-3">
    <a class="btn btn-success"
       href="${pageContext.request.contextPath}/views/ong/create-case.jsp">
        ➕ Créer un cas
    </a>
</div>

<!-- LISTE DES CAS -->
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
    <th>Actions</th>
</tr>
</thead>

<tbody>

<c:forEach var="c" items="${cases}">
<tr>
    <td>${c.titre}</td>

    <td>${c.description}</td>

    <!-- DONS -->
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

    <!-- 🔴 ACTIONS MODIFIER / SUPPRIMER -->
    <td>
        <a class="btn btn-warning btn-sm"
           href="${pageContext.request.contextPath}/editCase?id=${c.id}">
           ✏ Modifier
        </a>
        
        

        <a class="btn btn-danger btn-sm"
           href="${pageContext.request.contextPath}/case/delete?id=${c.id}"
           onclick="return confirm('Voulez-vous vraiment supprimer ce cas ?')">
           🗑 Supprimer
        </a>
    </td>

</tr>
</c:forEach>

</tbody>
</table>

</c:if>

<!-- PAGINATION -->
<c:if test="${totalPages > 1}">
<nav class="mt-4">
<ul class="pagination justify-content-center">

<li class="page-item ${page == 1 ? 'disabled' : ''}">
  <a class="page-link" href="?page=${page - 1}">
     Précédent
  </a>
</li>

<c:forEach begin="1" end="${totalPages}" var="p">
<li class="page-item ${p == page ? 'active' : ''}">
  <a class="page-link" href="?page=${p}">
     ${p}
  </a>
</li>
</c:forEach>

<li class="page-item ${page == totalPages ? 'disabled' : ''}">
  <a class="page-link" href="?page=${page + 1}">
     Suivant
  </a>
</li>

</ul>
</nav>
</c:if>

</div>
</div>

</div>
</body>
</html>
