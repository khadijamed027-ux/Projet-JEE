<%@ page contentType="text/html;charset=UTF-8" %>
<%
    com.ongconnect.model.User user = (com.ongconnect.model.User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Signaler un cas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Signaler un cas</h2>

            <form action="<%= request.getContextPath() %>/case/create" method="post">
                <div class="mb-3">
                    <label for="titre" class="form-label">Titre</label>
                    <input type="text" class="form-control" id="titre" name="titre" required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="4"></textarea>
                </div>

                <div class="mb-3">
                    <label for="localisation" class="form-label">Localisation</label>
                    <input type="text" class="form-control" id="localisation" name="localisation">
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-success">Envoyer</button>
                </div>
            </form>

            <a href="<%= request.getContextPath() %>/jsp/user/dashboard.jsp" class="btn btn-secondary mt-3">Retour au tableau de bord</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>