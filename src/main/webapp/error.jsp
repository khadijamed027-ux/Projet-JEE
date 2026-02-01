<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Erreur</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="container">
<h2>Une erreur est survenue</h2>

<p>${error}</p>

<a href="${pageContext.request.contextPath}/index.jsp">
Retour à l’accueil
</a>

</div>

</body>
</html>
