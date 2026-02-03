<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>

<meta charset="UTF-8">
<title>ONG refusée</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
  background: #f4f6f9;
}

.card {
  max-width: 800px;
  margin: 60px auto;
  border-radius: 12px;
}

.icon {
  font-size: 60px;
}
</style>

</head>

<body>

<div class="card shadow">
<div class="card-body text-center">

<div class="icon">❌</div>

<h3 class="mt-3 text-danger">ONG refusée</h3>

<p class="mt-3">
  Désolé, votre ONG n’a pas été validée par l’administrateur.
</p>

<div class="alert alert-light text-start mt-4">
<b>Vous pouvez :</b>
<ul>
  <li>Vérifier les informations saisies</li>
  <li>Améliorer la description de l’ONG</li>
  <li>Recréer une nouvelle demande</li>
</ul>
</div>

<div class="mt-4">
  <a href="${pageContext.request.contextPath}/views/ong/create-ong.jsp"
     class="btn btn-primary">
     Recréer une ONG
  </a>

  <a href="${pageContext.request.contextPath}/logout"
     class="btn btn-secondary">
     Déconnexion
  </a>
</div>

</div>
</div>

</body>
</html>
