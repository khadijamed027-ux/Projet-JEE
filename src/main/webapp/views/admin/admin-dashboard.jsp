<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>

<title>Dashboard Admin</title>

<!-- BOOTSTRAP + ICONES -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>
body {
  background:#f0f2f5;
  font-family:'Segoe UI',sans-serif;
}

/* NAVBAR */
.navbar {
  background:#1e293b;
}
.navbar a {
  color:white !important;
  font-weight:600;
}

/* CARTES STATS */
.stat-card {
  border:none;
  border-radius:16px;
  transition:.3s;
  box-shadow:0 10px 25px rgba(0,0,0,0.06);
}
.stat-card:hover {
  transform:translateY(-6px);
}

.icon-box {
  width:60px;height:60px;
  border-radius:12px;
  display:flex;
  align-items:center;
  justify-content:center;
  font-size:26px;
}

/* TABLE */
.table-card {
  border-radius:16px;
  box-shadow:0 10px 25px rgba(0,0,0,0.06);
}

/* TITRES */
.title {
  font-weight:700;
  color:#1e293b;
}
</style>

</head>

<body>

<!-- NAV -->
<nav class="navbar navbar-expand-lg px-4 py-3">
  <a class="navbar-brand" href="#">🛡 Admin Panel</a>

  <div class="ms-auto">
    <a class="me-4" href="${pageContext.request.contextPath}/notifications">
      <i class="bi bi-bell"></i> Notifications

      <c:if test="${badgeAdmin > 0}">
        <span class="badge bg-danger">${badgeAdmin}</span>
      </c:if>
    </a>

    <a href="${pageContext.request.contextPath}/logout">
      <i class="bi bi-box-arrow-right"></i> Déconnexion
    </a>
  </div>
</nav>

<div class="container my-4">

<h3 class="title mb-4">📊 Vue d’ensemble</h3>

<!-- ========== CARTES ========== -->
<div class="row g-4">

<!-- ONG -->
<div class="col-md-4">
<div class="card stat-card p-3">
<div class="d-flex align-items-center">
<div class="icon-box bg-primary text-white me-3">
<i class="bi bi-building"></i>
</div>

<div>
<h6 class="text-muted">ONG</h6>
<h3>${totalOngs}</h3>
</div>
</div>
</div>
</div>

<!-- CAS -->
<div class="col-md-4">
<div class="card stat-card p-3">
<div class="d-flex align-items-center">
<div class="icon-box bg-success text-white me-3">
<i class="bi bi-clipboard-heart"></i>
</div>

<div>
<h6 class="text-muted">Cas</h6>
<h3>${totalCases}</h3>
</div>
</div>
</div>
</div>

<!-- DONS -->
<div class="col-md-4">
<div class="card stat-card p-3">
<div class="d-flex align-items-center">
<div class="icon-box bg-warning text-white me-3">
<i class="bi bi-cash-coin"></i>
</div>

<div>
<h6 class="text-muted">Dons</h6>
<h3>${totalDonations}</h3>
</div>
</div>
</div>
</div>

</div>

<!-- ========== GRAPHIQUES ========== -->
<div class="row mt-4">

<div class="col-md-6">
<div class="card p-3 table-card">
<h5>Répartition globale</h5>
<canvas id="pieChart"></canvas>
</div>
</div>

<div class="col-md-6">
<div class="card p-3 table-card">
<h5>Comparaison</h5>
<canvas id="barChart"></canvas>
</div>
</div>

</div>

<!-- ========== ONG EN ATTENTE ========== -->
<div class="card table-card p-3 mt-4">

<h5>⏳ ONG en attente</h5>

<table class="table mt-3">
<tr class="table-light">
<th>Nom</th>
<th>Domaine</th>
<th>Action</th>
</tr>

<c:forEach var="o" items="${pendingOngs}">
<tr>
<td>${o.nom}</td>
<td>${o.domaine}</td>

<td>
<a class="btn btn-success btn-sm"
href="${pageContext.request.contextPath}/admin/validateONG?ongId=${o.id}">
✔ Valider
</a>

<a class="btn btn-danger btn-sm"
href="${pageContext.request.contextPath}/admin/rejectONG?ongId=${o.id}">
✖ Refuser
</a>
</td>
</tr>
</c:forEach>

</table>
</div>

</div>

<!-- ========== SCRIPTS GRAPHES ========== -->

<script>
new Chart(document.getElementById("pieChart"), {
type:'pie',
data:{
labels:['ONG','Cas','Dons'],
datasets:[{
data:[
${totalOngs},
${totalCases},
${totalDonations}
],
backgroundColor:['#3b82f6','#22c55e','#f59e0b']
}]
}
});

new Chart(document.getElementById("barChart"), {
type:'bar',
data:{
labels:['ONG','Cas','Dons'],
datasets:[{
label:'Total',
data:[
${totalOngs},
${totalCases},
${totalDonations}
],
backgroundColor:['#3b82f6','#22c55e','#f59e0b']
}]
}
});
</script>

</body>
</html>
