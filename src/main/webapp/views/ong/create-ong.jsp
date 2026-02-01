<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Créer mon ONG</title>

<style>
body {
    background:#f4f6f9;
    font-family:Arial;
}
.box {
    width:600px;
    margin:40px auto;
    background:white;
    padding:25px;
    border-radius:10px;
}
input, textarea, select {
    width:100%;
    padding:10px;
    margin-top:10px;
}
button {
    margin-top:15px;
    padding:10px;
    background:#27ae60;
    color:white;
    border:none;
}
.error { color:red; }
</style>

</head>

<body>

<div class="box">

<h2>Créer votre ONG</h2>

<c:if test="${not empty error}">
   <p class="error">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/ong/create"
      method="post">

<input name="nom" placeholder="Nom de l’ONG" required />

<textarea name="description"
          placeholder="Description de l’ONG"
          required></textarea>

<select name="domaine" required>
   <option value="">-- Domaine --</option>
   <option value="SANTE">Santé</option>
   <option value="EDUCATION">Éducation</option>
   <option value="ENVIRONNEMENT">Environnement</option>
</select>

<button type="submit">Enregistrer mon ONG</button>

</form>

</div>

</body>
</html>
