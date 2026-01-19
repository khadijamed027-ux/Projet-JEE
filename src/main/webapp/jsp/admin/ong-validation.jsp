<%@ page contentType="text/html;charset=UTF-8" %>
<%
    com.ongconnect.model.User user = (com.ongconnect.model.User) session.getAttribute("user");
    if(user == null || !user.getRole().name().equals("ADMIN")) {
        response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
        return;
    }

    // Récupérer la liste des ONG depuis l'attribut request
    java.util.List<com.ongconnect.model.ONG> ongs = (java.util.List<com.ongconnect.model.ONG>) request.getAttribute("ongs");
    if(ongs == null) {
        ongs = new java.util.ArrayList<>();
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Validation des ONG</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Validation des ONG</h2>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Statut</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(com.ongconnect.model.ONG o : ongs) { %>
                        <tr>
                            <td><%= o.getNom() %></td>
                            <td><%= o.getStatutValidation() %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/admin/validate?id=<%= o.getId() %>" class="btn btn-sm btn-success">Valider</a>
                                <a href="<%= request.getContextPath() %>/admin/refuse?id=<%= o.getId() %>" class="btn btn-sm btn-danger">Refuser</a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <a href="<%= request.getContextPath() %>/jsp/admin/admin-dashboard.jsp" class="btn btn-secondary mt-3">Retour au dashboard</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>