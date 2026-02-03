<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>ONG en attente</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
  background: #f4f6f9;
}
.card {
  max-width: 700px;
  margin: 80px auto;
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

    <div class="icon">⏳</div>

    <h3 class="mt-3">ONG en attente de validation</h3>

    <p class="text-muted mt-3">
      Votre organisation a bien été enregistrée,  
      mais elle doit être validée par l’administrateur  
      avant d’accéder au tableau de bord.
    </p>

    <div class="alert alert-warning mt-4">
      Vous recevrez une notification dès que la validation sera effectuée.
    </div>

    <a href="${pageContext.request.contextPath}/logout"
       class="btn btn-danger mt-3">
       Déconnexion
    </a>

  </div>
</div>

</body>
</html>
