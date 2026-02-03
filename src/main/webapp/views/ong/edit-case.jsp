<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Modifier le cas</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
  background: #f4f6f9;
}

.card {
  max-width: 900px;
  margin: 40px auto;
  border-radius: 12px;
}
</style>

</head>

<body>

<div class="card shadow">
<div class="card-body">

<h3 class="text-center mb-4">✏️ Modifier le cas</h3>

<form method="post"
      action="${pageContext.request.contextPath}/editCase">

<input type="hidden" name="id" value="${cas.id}" />

<div class="mb-3">
  <label class="form-label">Titre</label>
  <input name="titre"
         value="${cas.titre}"
         class="form-control"
         required />
</div>

<div class="mb-3">
  <label class="form-label">Description</label>
  <textarea name="description"
            class="form-control"
            rows="5"
            required>${cas.description}</textarea>
</div>

<div class="mb-3">
  <label class="form-label">Localisation</label>
  <input name="localisation"
         value="${cas.localisation}"
         class="form-control" />
</div>

<div class="mb-3">
  <label class="form-label">Objectif (MRU)</label>
  <input name="objectif"
         type="number"
         step="0.1"
         value="${cas.objectif}"
         class="form-control"
         required />
</div>

<div class="text-end">
  <a href="${pageContext.request.contextPath}/ong/dashboard"
     class="btn btn-secondary">
     Annuler
  </a>

  <button class="btn btn-success">
    Enregistrer les modifications
  </button>
</div>

</form>

</div>
</div>

</body>
</html>
