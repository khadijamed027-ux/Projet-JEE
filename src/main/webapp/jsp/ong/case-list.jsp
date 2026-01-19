<%@ page contentType="text/html;charset=UTF-8" %>
<%
    com.ongconnect.model.User user = (com.ongconnect.model.User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
        return;
    }

    // Exemple : récupérer la liste des cas depuis la session ou l'attribut request
    java.util.List<com.ongconnect.model.CaseReport> cases = (java.util.List<com.ongconnect.model.CaseReport>) request.getAttribute("cases");
    if(cases == null) {
        cases = new java.util.ArrayList<>();
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des cas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Liste des cas</h2>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Titre</th>
                        <th>Statut</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(com.ongconnect.model.CaseReport c : cases) { %>
                        <tr>
                            <td><%= c.getTitre() %></td>
                            <td><%= c.getStatut() %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/case/update?id=<%= c.getId() %>" class="btn btn-sm btn-primary">Traiter</a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <a href="<%= request.getContextPath() %>/jsp/ong/ong-dashboard.jsp" class="btn btn-secondary mt-3">Retour au dashboard</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>