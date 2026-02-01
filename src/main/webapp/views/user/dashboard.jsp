<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Tableau de bord</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<nav class="navbar">
<a href="${pageContext.request.contextPath}/index.jsp">Accueil</a>
<a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
</nav>

<div class="container">

<h2>Bienvenue ${sessionScope.user.nom}</h2>

<div class="cards">

<a class="card" href="${pageContext.request.contextPath}/index.jsp">
Voir les ONG
</a>

<a class="card" href="${pageContext.request.contextPath}/views/public/donation-success.jsp">
Mes dons
</a>

</div>

</div>

</body>
</html>
