<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ONG refusée</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container mt-5">
  <div class="alert alert-danger text-center shadow p-4">

    <h3>❌ ONG refusée</h3>

    <p class="mt-3">
      Désolé, votre ONG n’a pas été validée par l’administrateur.
    </p>

    <p>
      Vous pouvez :
    </p>

    <ul class="text-start d-inline-block">
      <li>Modifier les informations de votre ONG</li>
      <li>Contacter l’administrateur pour plus de détails</li>
      <li>Créer une nouvelle demande</li>
    </ul>

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
