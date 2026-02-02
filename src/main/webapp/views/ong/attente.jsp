<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attente</title>
</head>
<body>
<div class="alert alert-warning text-center mt-5">
  <h3>⏳ ONG en attente</h3>
  <p>
    Votre ONG n’est pas encore validée par l’administrateur.
  </p>
  <p>Vous recevrez un accès après validation.</p>

  <a href="${pageContext.request.contextPath}/logout"
     class="btn btn-danger">
     Déconnexion
  </a>
</div>

</body>
</html>