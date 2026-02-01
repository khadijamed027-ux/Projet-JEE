<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>

<style>
body { font-family: Arial; background:#f4f6f9; margin:0; }

.admin-nav {
  background:#2c3e50; padding:15px;
}
.admin-nav a {
  color:white; margin-right:20px; text-decoration:none; font-weight:bold;
}

.stats {
  display:flex; gap:20px; margin:30px;
}
.stats .card {
  flex:1; background:white; padding:25px;
  text-align:center; border-radius:12px;
}
.stats b { font-size:32px; color:#27ae60; }

table {
  width:90%; margin:30px auto; border-collapse:collapse;
}
th, td {
  padding:12px; border-bottom:1px solid #ddd;
}
a.btn {
  padding:6px 10px; border-radius:6px;
  text-decoration:none; color:white;
}
.validate { background:#27ae60; }
.reject { background:#c0392b; }
</style>
</head>

<body>

<nav class="admin-nav">
  <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
  <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
</nav>

<div class="stats">
  <div class="card">ONG<br><b>${totalOngs}</b></div>
  <div class="card">Cas<br><b>${totalCases}</b></div>
  <div class="card">Dons<br><b>${totalDonations}</b></div>
</div>

<h2 style="margin-left:5%">ONG en attente</h2>

<table>
<tr>
  <th>Nom</th>
  <th>Domaine</th>
  <th>Action</th>
</tr>

<c:forEach var="o" items="${pendingOngs}">
<tr>
  <td>${o.nom}</td>
  <td>${o.domaine}</td>
  <td>
    <a class="btn validate"
       href="${pageContext.request.contextPath}/admin/validateONG?ongId=${o.id}">
       Valider
    </a>
    <a class="btn reject"
       href="${pageContext.request.contextPath}/admin/rejectONG?ongId=${o.id}">
       Refuser
    </a>
  </td>
</tr>
</c:forEach>
</table>

</body>
</html>
