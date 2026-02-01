<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Créer un cas - ONG</title>

<style>
* { box-sizing: border-box; }

body {
    font-family: 'Segoe UI', Arial;
    background: #f4f6f9;
    margin: 0;
}

.header {
    background: linear-gradient(135deg, #27ae60, #1e8449);
    color: white;
    padding: 25px;
    text-align: center;
}

.container {
    width: 90%;
    max-width: 700px;
    margin: 30px auto;
}

.card {
    background: white;
    padding: 25px;
    border-radius: 12px;
    box-shadow: 0 5px 15px rgba(0,0,0,0.08);
}

label {
    font-weight: bold;
    display: block;
    margin-top: 15px;
}

input, textarea, select {
    width: 100%;
    padding: 10px;
    margin-top: 6px;
    border-radius: 6px;
    border: 1px solid #ccc;
}

textarea {
    min-height: 120px;
}

.btn {
    display: inline-block;
    margin-top: 20px;
    padding: 12px;
    width: 100%;
    background: #27ae60;
    color: white;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
}

.btn:hover {
    background: #1e8449;
}

.back {
    display: block;
    margin-top: 10px;
    text-align: center;
    text-decoration: none;
    color: #555;
}

.error {
    background: #ffebee;
    color: #c0392b;
    padding: 10px;
    border-radius: 6px;
    margin-bottom: 10px;
}

</style>
</head>

<body>

<div class="header">
    <h1>Publier un nouveau cas</h1>
</div>

<div class="container">

<div class="card">

<h2>Formulaire du cas</h2>

<!-- MESSAGE ERREUR -->
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/ong/case/create"
      method="post">

    <label>Titre du cas</label>
    <input name="titre"
           placeholder="Ex: Aide médicale pour enfant"
           required />

    <label>Description</label>
    <textarea name="description"
              placeholder="Décrivez la situation..."
              required></textarea>

    <label>Localisation</label>
    <input name="localisation"
           placeholder="Ville / Région"
           required />

    <label>Domaine</label>
    <select name="typeCase" required>
        <option value="">-- Choisir --</option>
        <option value="SANTE">Santé</option>
        <option value="EDUCATION">Éducation</option>
        <option value="ENVIRONNEMENT">Environnement</option>
    </select>

    <button type="submit" class="btn">
        Publier le cas
    </button>

</form>

<a class="back"
   href="${pageContext.request.contextPath}/ong/dashboard">
   ← Retour au dashboard
</a>

</div>
</div>

</body>
</html>
